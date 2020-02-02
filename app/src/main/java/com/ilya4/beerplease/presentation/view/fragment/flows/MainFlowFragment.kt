package com.ilya4.beerplease.presentation.view.fragment.flows

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
import com.ilya4.beerplease.presentation.presenter.FlowMainPresenter
import com.ilya4.beerplease.presentation.view.fragment.*
import com.ilya4.beerplease.presentation.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.MainMvpView
import com.ilya4.beerplease.presentation.base.BaseTabFragment
import com.ilya4.beerplease.utils.FragmentUtils.Companion.addAdditionalTabFragment
import com.ilya4.beerplease.utils.FragmentUtils.Companion.addInitialTabFragment
import com.ilya4.beerplease.utils.FragmentUtils.Companion.addShowHideFragment
import com.ilya4.beerplease.utils.FragmentUtils.Companion.removeFragment
import com.ilya4.beerplease.utils.FragmentUtils.Companion.showHideTabFragment
import com.ilya4.beerplease.utils.StackListManager.Companion.updateStackIndex
import com.ilya4.beerplease.utils.StackListManager.Companion.updateStackToIndexFirst
import com.ilya4.beerplease.utils.StackListManager.Companion.updateTabStackIndex
import com.ilya4.beerplease.utils.Utils
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap


class MainFlowFragment: BaseFragment<FlowMainPresenter>(R.layout.activity_main), MainMvpView  {

    @InjectPresenter
    lateinit var presenter: FlowMainPresenter
    @ProvidePresenter
    override fun providePresenter(): FlowMainPresenter {
        return super.providePresenter()
    }

    private lateinit var keyboardLayoutListener: ViewTreeObserver.OnGlobalLayoutListener
    private var prevContentHeight = 0
    private var isKeyboardShown = false

    private lateinit var tagStacks: MutableMap<String, Stack<String>>
    private lateinit var currentStack: String
    private lateinit var stackList: MutableList<String>
    private lateinit var menuStacks: MutableList<String>
    private lateinit var currentFragment: Fragment
    private lateinit var searchFragment: Fragment
    private lateinit var profileFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonListeners()

        createStacks()
        initKeyboardLayoutListener()
    }

    override fun onResume() {
        super.onResume()
        rootView.viewTreeObserver.addOnGlobalLayoutListener(keyboardLayoutListener)
    }

    override fun onStop() {
        super.onStop()
        rootView.viewTreeObserver.removeOnGlobalLayoutListener(keyboardLayoutListener)
    }

    private fun initButtonListeners() {
        scanFab.setOnClickListener { presenter.goToFindBeerByBarcode() }
    }

    override fun onBackPressed() {
        resolveBackPressed()
    }

    fun showBeerCardFragment(tab: String, addToBackStack: Boolean, bundle: Bundle) {
        val commonBundle = Bundle()
        commonBundle.putString(DATA_KEY_1, tab)
        commonBundle.putBoolean(DATA_KEY_2, addToBackStack)
        showFragment(commonBundle, BeerCardTabFragment().newInstance(bundle, false))
    }

    fun showAddNewBeerFragment(tab: String, addToBackStack: Boolean, bundle: Bundle) {
        val commonBundle = Bundle()
        commonBundle.putString(DATA_KEY_1, tab)
        commonBundle.putBoolean(DATA_KEY_2, addToBackStack)
        showFragment(commonBundle, AddNewBeerTabFragment().newInstance(bundle, false))
    }

    fun showChooseBeerStyleFragment(tab: String, addToBackStack: Boolean, bundle: Bundle) {
        val commonBundle = Bundle()
        commonBundle.putString(DATA_KEY_1, tab)
        commonBundle.putBoolean(DATA_KEY_2, addToBackStack)
        showFragment(commonBundle, ChooseBeerStyleTabFragment().newInstance(bundle, false))
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
        bottomNavigationView?.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val bundle = Bundle()
        searchFragment = SearchTabFragment().newInstance(bundle, true)
        profileFragment = UserProfileTabFragment().newInstance(bundle, true)

        tagStacks = LinkedHashMap()
        tagStacks[TAB_SEARCH] = Stack()
        tagStacks[TAB_PROFILE] = Stack()

        menuStacks = ArrayList()
        menuStacks.add(TAB_SEARCH)

        stackList = ArrayList()
        stackList.add(TAB_SEARCH)
        stackList.add(TAB_PROFILE)

        bottomNavigationView?.selectedItemId = R.id.tab_search
        bottomNavigationView?.setOnNavigationItemReselectedListener(onNavigationItemReselectedListener)
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
        currentStack = tabId
        BaseTabFragment.currentTab = currentStack

        if (tagStacks[tabId]?.size == 0) {

            when(tabId) {
                TAB_SEARCH -> {
                    addInitialTabFragment(childFragmentManager, tagStacks, TAB_SEARCH, searchFragment, R.id.container, true)
                    resolveStackLists(tabId)
                    assignCurrentFragment(searchFragment)
                }
                TAB_PROFILE -> {
                    addAdditionalTabFragment(childFragmentManager, tagStacks, TAB_PROFILE, profileFragment, currentFragment, R.id.container, true)
                    resolveStackLists(tabId)
                    assignCurrentFragment(profileFragment)
                }
            }
        } else {
            val targetFragment = childFragmentManager.findFragmentByTag(tagStacks[tabId]?.lastElement())
            targetFragment?.let {
                showHideTabFragment(childFragmentManager, it, currentFragment)
                resolveStackLists(tabId)
                assignCurrentFragment(it)
            }
        }
    }

    private fun popStackExceptFirst() {
        if (tagStacks[currentStack]?.size == 1) {
            return
        }
        while (!tagStacks[currentStack]?.empty()!!
            && !childFragmentManager.findFragmentByTag(tagStacks[currentStack]?.peek())?.arguments?.getBoolean(EXTRA_IS_ROOT_FRAGMENT)!!) { //TODO переделать
            val removeFragment = childFragmentManager.findFragmentByTag(tagStacks[currentStack]?.peek())
            removeFragment?.let {
                childFragmentManager.beginTransaction().remove(it)
            }
            tagStacks[currentStack]?.pop()
        }
        val fragment = childFragmentManager.findFragmentByTag(tagStacks[currentStack]?.elementAt(0))
        fragment?.let {
            removeFragment(childFragmentManager, it, currentFragment)
            assignCurrentFragment(it)
        }
    }

    private fun resolveBackPressed() {
        var stackValue = 0
        if (tagStacks[currentStack]?.size == 1) {
            val value = tagStacks[stackList[1]]
            if (value?.size!! > 1) {
                stackValue = value.size
                popAndNavigateToPreviousMenu()
            }
            if (stackValue <= 1) {
                if (menuStacks.size > 1)
                    navigateToPreviousMenu()
               else
                   activity?.finish()
            }
        } else {
            popFragment()
        }
    }

    private fun popFragment() {
        val fragmentTag = tagStacks[currentStack]?.elementAt(tagStacks[currentStack]?.size!! - 2)
        val fragment = childFragmentManager.findFragmentByTag(fragmentTag)
        tagStacks[currentStack]?.pop()

        removeFragment(childFragmentManager, fragment!!, currentFragment)

        assignCurrentFragment(fragment)
    }

    private fun popAndNavigateToPreviousMenu() {
        val tempCurrent = stackList[0]
        currentStack = stackList[1]
        BaseTabFragment.currentTab = currentStack
        bottomNavigationView.selectedItemId = resolveTabPosition(currentStack)
        val targetFragment = childFragmentManager.findFragmentByTag(tagStacks[currentStack]?.lastElement())
        showHideTabFragment(childFragmentManager, targetFragment!!, currentFragment)
        assignCurrentFragment(targetFragment)
        updateStackToIndexFirst(stackList, tempCurrent)
        menuStacks.removeAt(0)
    }

    private fun navigateToPreviousMenu() {
        menuStacks.removeAt(0)
        currentStack = menuStacks[0]
        BaseTabFragment.currentTab = currentStack
        bottomNavigationView.selectedItemId = resolveTabPosition(currentStack)
        val targetFragment = childFragmentManager.findFragmentByTag(tagStacks[currentStack]?.lastElement())
        showHideTabFragment(childFragmentManager, targetFragment!!, currentFragment)
        assignCurrentFragment(targetFragment)
    }

    private fun showFragment(bundle: Bundle, fragmentToAdd: Fragment) {
        val tab = bundle.getString(DATA_KEY_1)
        val shouldAdd = bundle.getBoolean(DATA_KEY_2)
        addShowHideFragment(childFragmentManager, tagStacks, tab!!, fragmentToAdd, getCurrentFragmentFromShownStack(), R.id.container, shouldAdd)
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
        return childFragmentManager.findFragmentByTag(tagStacks[currentStack]?.elementAt(tagStacks[currentStack]?.size!! - 1))!!
    }

    private fun resolveStackLists(tabId: String) {
        updateStackIndex(stackList, tabId)
        updateTabStackIndex(menuStacks, tabId)
    }

    private fun assignCurrentFragment(currentFragment: Fragment) {
        this.currentFragment = currentFragment
    }
}