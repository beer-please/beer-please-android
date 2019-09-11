package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.view.get
import com.github.zawadz88.materialpopupmenu.popupMenu
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.FBeerCardPresenter
import com.ilya4.beerplease.presentation.view.activity.MainActivity
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.FBeerCardMvpView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_beer_card.*
import javax.inject.Inject

class BeerCardFragment: BaseFragment(), FBeerCardMvpView {

    @Inject
    lateinit var presenter: FBeerCardPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_beer_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()

        fixHideBottomBarOnScroll()
        initToolbar()
    }

    private fun fixHideBottomBarOnScroll() {
        val activity = activity as MainActivity
        activity.initOnScrollListener(mainContent)
    }

    private fun initToolbar() {
        beerToolbar.inflateMenu(R.menu.beer_card_menu)
        beerToolbar.setNavigationOnClickListener {  activity?.onBackPressed()}
        beerToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_toolbar -> {
                    showPopupMainMenu()
                    true
                }
                else -> false
            }
        }
    }

    private fun showPopupMainMenu() {
        val popupMenu = popupMenu {
            style = R.style.Widget_MPM_Menu_CustomBackground
            section {
                item {
                    label = getString(R.string.beer_card_menu_suggest_changes)
                    icon = R.drawable.ic_suggest_changes_btn
                }
                item {
                    label = getString(R.string.beer_card_menu_report_duplicate)
                    icon = R.drawable.ic_duplicate_btn
                }
                item {
                    label = getString(R.string.beer_card_menu_report_problem)
                    icon = R.drawable.ic_report_problem_btn
                }
            }
        }
        if (context != null)
            popupMenu.show(context!!, beerToolbar[3])
    }

    companion object {
        const val TAG = "BeerCardFragment"
    }
}