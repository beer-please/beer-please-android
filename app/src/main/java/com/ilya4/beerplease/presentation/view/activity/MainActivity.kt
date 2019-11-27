package com.ilya4.beerplease.presentation.view.activity

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.app.Constants.DATA_KEY_1
import com.ilya4.beerplease.presentation.app.Constants.DATA_KEY_2
import com.ilya4.beerplease.presentation.app.Constants.EXTRA_IS_ROOT_FRAGMENT
import com.ilya4.beerplease.presentation.app.Constants.TAB_PROFILE
import com.ilya4.beerplease.presentation.app.Constants.TAB_SEARCH
import com.ilya4.beerplease.presentation.presenter.AMainPresenter
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.fragment.*
import com.ilya4.beerplease.presentation.view.view.AMainMvpView
import dagger.android.AndroidInjection


import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import com.ilya4.beerplease.utils.FragmentUtils.Companion.addAdditionalTabFragment
import com.ilya4.beerplease.utils.FragmentUtils.Companion.addInitialTabFragment
import com.ilya4.beerplease.utils.FragmentUtils.Companion.addShowHideFragment
import com.ilya4.beerplease.utils.FragmentUtils.Companion.removeFragment
import com.ilya4.beerplease.utils.FragmentUtils.Companion.showHideTabFragment
import com.ilya4.beerplease.utils.StackListManager.Companion.updateStackIndex
import com.ilya4.beerplease.utils.StackListManager.Companion.updateStackToIndexFirst
import com.ilya4.beerplease.utils.StackListManager.Companion.updateTabStackIndex
import com.ilya4.beerplease.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap


class MainActivity: BaseActivity<AMainPresenter>(R.layout.activity_main), AMainMvpView  {

    @InjectPresenter
    lateinit var presenter: AMainPresenter
    @ProvidePresenter
    override fun providePresenter(): AMainPresenter {
        return super.providePresenter()
    }

    private lateinit var keyboardLayoutListener: ViewTreeObserver.OnGlobalLayoutListener
    private var prevContentHeight = 0
    private var isKeyboardShown = false

    private lateinit var tagStacks: MutableMap<String, Stack<String>>
    private lateinit var currentTab: String
    private lateinit var stackList: MutableList<String>
    private lateinit var menuStacks: MutableList<String>
    private lateinit var currentFragment: Fragment
    private lateinit var searchFragment: Fragment
    private lateinit var profileFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initButtonListeners()

        createStacks()
        initKeyboardLayoutListener()
    }

    override fun onResume() {
        super.onResume()
        rootView.viewTreeObserver.addOnGlobalLayoutListener(keyboardLayoutListener)
    }

    private fun initButtonListeners() {
        scanFab.setOnClickListener { startFindProfileBeerActivity() }
    }

    override fun onBackPressed() {
        resolveBackPressed()
    }

    private fun startFindProfileBeerActivity() {
        startActivity(FindProfileBeerByBarcodeActivity::class.java, false)
    }

    fun showBeerCardFragment(tab: String, addToBackStack: Boolean, bundle: Bundle) {
        val commonBundle = Bundle()
        commonBundle.putString(DATA_KEY_1, tab)
        commonBundle.putBoolean(DATA_KEY_2, addToBackStack)
        showFragment(commonBundle, BeerCardFragment().newInstance(bundle, false))
    }

    fun showAddNewBeerFragment(tab: String, addToBackStack: Boolean, bundle: Bundle) {
        val commonBundle = Bundle()
        commonBundle.putString(DATA_KEY_1, tab)
        commonBundle.putBoolean(DATA_KEY_2, addToBackStack)
        showFragment(commonBundle, AddNewBeerFragment().newInstance(bundle, false))
    }

    fun showChooseBeerStyleFragment(tab: String, addToBackStack: Boolean, bundle: Bundle) {
        val commonBundle = Bundle()
        commonBundle.putString(DATA_KEY_1, tab)
        commonBundle.putBoolean(DATA_KEY_2, addToBackStack)
        showFragment(commonBundle, ChooseBeerStyleFragment().newInstance(bundle, false))
    }

    fun initOnScrollListener(nestedScrollView: NestedScrollView) {
        Utils.initScrollListenerForBottomView(nestedScrollView, arrayOf(bottomBarWrapper, scanFab))
    }

    private fun initKeyboardLayoutListener() {
        keyboardLayoutListener = ViewTreeObserver.OnGlobalLayoutListener{
            if (rootView.height > 0) {
                val contentHeight = rootView.height

                if (!isKeyboardShown) {
                    if (contentHeight < prevContentHeight) {
                        isKeyboardShown = true
                        bottomBarWrapper.visibility = View.GONE
                        scanFab.visibility = View.GONE
                    }
                } else {
                    if (contentHeight > prevContentHeight) {
                        isKeyboardShown = false
                        bottomBarWrapper.postDelayed({
                            bottomBarWrapper.visibility = View.VISIBLE
                            scanFab.visibility = View.VISIBLE
                        }, 30)

                    }
                }
                prevContentHeight = contentHeight
            }
        }
    }

    private fun createStacks() {
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val bundle = Bundle()
        searchFragment = SearchFragment().newInstance(bundle, true)
        profileFragment = UserProfileFragment().newInstance(bundle, true)

        tagStacks = LinkedHashMap()
        tagStacks[TAB_SEARCH] = Stack()
        tagStacks[TAB_PROFILE] = Stack()

        menuStacks = ArrayList()
        menuStacks.add(TAB_SEARCH)

        stackList = ArrayList()
        stackList.add(TAB_SEARCH)
        stackList.add(TAB_PROFILE)

        bottomNavigationView.selectedItemId = R.id.tab_search
        bottomNavigationView.setOnNavigationItemReselectedListener(onNavigationItemReselectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId) {
            R.id.tab_search -> {
                selectedTab(TAB_SEARCH)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_profile -> {
                selectedTab(TAB_PROFILE)
                return@OnNavigationItemSelectedListener true
            }
            else -> return@OnNavigationItemSelectedListener false
        }
    }

    private val onNavigationItemReselectedListener = BottomNavigationView.OnNavigationItemReselectedListener {
        when(it.itemId) {
            R.id.tab_search -> popStackExceptFirst()
            R.id.tab_profile -> popStackExceptFirst()
        }
    }

    private fun selectedTab(tabId: String) {
        currentTab = tabId
        BaseFragment.setCurrentTab(currentTab)

        if (tagStacks[tabId]?.size == 0) {

            when(tabId) {
                TAB_SEARCH -> {
                    addInitialTabFragment(supportFragmentManager, tagStacks, TAB_SEARCH, searchFragment, R.id.container, true)
                    resolveStackLists(tabId)
                    assignCurrentFragment(searchFragment)
                }
                TAB_PROFILE -> {
                    addAdditionalTabFragment(supportFragmentManager, tagStacks, TAB_PROFILE, profileFragment, currentFragment, R.id.container, true)
                    resolveStackLists(tabId)
                    assignCurrentFragment(profileFragment)
                }
            }
        } else {
            val targetFragment = supportFragmentManager.findFragmentByTag(tagStacks[tabId]?.lastElement())
            showHideTabFragment(supportFragmentManager, targetFragment!!, currentFragment) //TODO переделать
            resolveStackLists(tabId)
            assignCurrentFragment(targetFragment)
        }
    }

    private fun popStackExceptFirst() {
        if (tagStacks[currentTab]?.size == 1) {
            return
        }
        while (!tagStacks[currentTab]?.empty()!!
            && !supportFragmentManager.findFragmentByTag(tagStacks[currentTab]?.peek())?.arguments?.getBoolean(EXTRA_IS_ROOT_FRAGMENT)!!) { //TODO переделать
            val removeFragment = supportFragmentManager.findFragmentByTag(tagStacks[currentTab]?.peek())
            supportFragmentManager.beginTransaction().remove(removeFragment!!) //TODO и тут
            tagStacks[currentTab]?.pop()
        }
        val fragment = supportFragmentManager.findFragmentByTag(tagStacks[currentTab]?.elementAt(0))
        removeFragment(supportFragmentManager, fragment!!, currentFragment) //TODO и это
        assignCurrentFragment(fragment)
    }

    private fun resolveBackPressed() {
        var stackValue = 0
        if (tagStacks[currentTab]?.size == 1) {
            val value = tagStacks[stackList[1]]
            if (value?.size!! > 1) {
                stackValue = value.size
                popAndNavigateToPreviousMenu()
            }
            if (stackValue <= 1) {
                if (menuStacks.size > 1)
                    navigateToPreviousMenu()
                else
                    finish()
            }
        } else {
            popFragment()
        }
    }

    private fun popFragment() {
        val fragmentTag = tagStacks[currentTab]?.elementAt(tagStacks[currentTab]?.size!! - 2)
        val fragment = supportFragmentManager.findFragmentByTag(fragmentTag)
        tagStacks[currentTab]?.pop()

        removeFragment(supportFragmentManager, fragment!!, currentFragment)

        assignCurrentFragment(fragment)
    }

    private fun popAndNavigateToPreviousMenu() {
        val tempCurrent = stackList[0]
        currentTab = stackList[1]
        BaseFragment.setCurrentTab(currentTab)
        bottomNavigationView.selectedItemId = resolveTabPosition(currentTab)
        val targetFragment = supportFragmentManager.findFragmentByTag(tagStacks[currentTab]?.lastElement())
        showHideTabFragment(supportFragmentManager, targetFragment!!, currentFragment)
        assignCurrentFragment(targetFragment)
        updateStackToIndexFirst(stackList, tempCurrent)
        menuStacks.removeAt(0)
    }

    private fun navigateToPreviousMenu() {
        menuStacks.removeAt(0)
        currentTab = menuStacks[0]
        BaseFragment.setCurrentTab(currentTab)
        bottomNavigationView.selectedItemId = resolveTabPosition(currentTab)
        val targetFragment = supportFragmentManager.findFragmentByTag(tagStacks[currentTab]?.lastElement())
        showHideTabFragment(supportFragmentManager, targetFragment!!, currentFragment)
        assignCurrentFragment(targetFragment)
    }

    private fun showFragment(bundle: Bundle, fragmentToAdd: Fragment) {
        val tab = bundle.getString(DATA_KEY_1)
        val shouldAdd = bundle.getBoolean(DATA_KEY_2)
        addShowHideFragment(supportFragmentManager, tagStacks, tab!!, fragmentToAdd, getCurrentFragmentFromShownStack(), R.id.container, shouldAdd)
        assignCurrentFragment(fragmentToAdd)
    }

    private fun resolveTabPosition(currentTab: String): Int {
        var tabIndex = 0
        when(currentTab) {
            TAB_SEARCH -> tabIndex = R.id.tab_search
            TAB_PROFILE -> tabIndex = R.id.tab_profile
        }
        return tabIndex
    }

    private fun getCurrentFragmentFromShownStack(): Fragment {
        return supportFragmentManager.findFragmentByTag(tagStacks[currentTab]?.elementAt(tagStacks[currentTab]?.size!! - 1))!!
    }

    private fun resolveStackLists(tabId: String) {
        updateStackIndex(stackList, tabId)
        updateTabStackIndex(menuStacks, tabId)
    }

    private fun assignCurrentFragment(currentFragment: Fragment) {
        this.currentFragment = currentFragment
    }
}