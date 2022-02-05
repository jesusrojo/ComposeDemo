package com.jesusrojo.composedemo.toolbar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusrojo.composedemo.toolbar.ToolbarTypeVo
import com.jesusrojo.composedemo.toolbar.state.ToolbarActionState
import com.jesusrojo.composedemo.toolbar.state.ToolbarState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ToolbarViewModel : ViewModel() {
    private val _toolbarState: MutableSharedFlow<ToolbarState> by lazy {
        MutableSharedFlow()
    }

    /**
     * Toolbar Flow for render view (usually subscribe in Activity)
     */
    val toolbarState: SharedFlow<ToolbarState>
        get() = _toolbarState.asSharedFlow()
    private val _toolbarActionState: MutableSharedFlow<ToolbarActionState> by lazy {
        MutableSharedFlow()
    }

    val toolbarTitle: StateFlow<String>
        get() = _toolbarTitle
    private val _toolbarTitle: MutableStateFlow<String> by lazy { MutableStateFlow(String()) }
    val toolbarLocation: StateFlow<String>
        get() = _toolbarLocation
    private val _toolbarLocation: MutableStateFlow<String> by lazy { MutableStateFlow(String()) }


    /**
     * Toolbar Flow for do actions depending on the toolbar
     */
    val toolbarActionState: SharedFlow<ToolbarActionState>
        get() {
            return _toolbarActionState.asSharedFlow()
        }

    private fun MutableSharedFlow<ToolbarState>.emitState(newState: ToolbarState) {
        viewModelScope.launch { this@emitState.emit(newState) }
    }

    private fun MutableSharedFlow<ToolbarActionState>.emitState(newState: ToolbarActionState) {
        viewModelScope.launch { this@emitState.emit(newState) }
    }

    fun setTitle(title: String) {
        viewModelScope.launch {
            _toolbarTitle.emit(title)
        }
    }

    fun setLocation(location: String) {
        viewModelScope.launch {
            _toolbarLocation.emit(location)
        }
    }

    fun showSearchToolbar() {
        _toolbarState.emitState(ToolbarState.ShowSearchToolbar)
    }

    fun showTitleToolbar(type:ToolbarTypeVo = ToolbarTypeVo.Back) {
        _toolbarState.emitState(ToolbarState.ShowTitleToolbar(type = type))
    }

    fun goBack() {
        _toolbarState.emitState(ToolbarState.GoBack)
    }

    fun onNotificationToolbarPressed() {
        _toolbarActionState.emitState(ToolbarActionState.OnNotificationsPressed)
    }

    fun onIconToolbarPressed() {
        _toolbarActionState.emitState(ToolbarActionState.OnIconLogoPressed)
    }

    fun onSearchToolbarPressed() {
        _toolbarActionState.emitState(ToolbarActionState.OnHomeSearchPressed)
    }

    fun onLocationToolbarPressed() {

    }

    fun showHomeToolbar() {
        _toolbarState.emitState(ToolbarState.ShowHomeToolbar)
    }

    fun showHomeDeliveryManToolbar() {
        _toolbarState.emitState(ToolbarState.ShowHomeDeliveryManToolbar)
    }

    fun hideHomeToolbar() {
        _toolbarState.emitState(ToolbarState.HideHomeToolbar)
    }

    fun onSearchToolbarDeletePressed() {
        _toolbarActionState.emitState(ToolbarActionState.ResetSearchScreen)
    }

//    private fun createProductImageVo() = ProductImageVo(
//        imageUri = "https://cdn.pixabay.com/photo/2021/07/19/16/04/pizza-6478478_960_720.jpg",
//        promotion = PromotionTypeVo.TwoForOne
//    )

    fun showTitleAndFilterToolbar(filterAction: (() -> Unit)? = null) {
        _toolbarState.emitState(ToolbarState.ShowTitleWithFilterToolbar(filterAction))
    }

    fun onSearchToolbarSearchPressed(searchText: String) {
        if (searchText.isNotEmpty()) {
            _toolbarActionState.emitState(ToolbarActionState.OnSearchFragmentSearchPressed(searchText))
        }
    }

    fun onFilterToolbarPressed(filterAction: (() -> Unit)?) {
        filterAction?.invoke()
    }

//
//    fun renderDeliveryManHome(deliveryManHomeVo: Deliveryman.HomeVo) {
//        _toolbarState.emitState(ToolbarState.RenderDeliveryManHome(deliveryManHomeVo))
//    }

//
//    // Mock
//    private fun createSearchResultItems(): List<SearchResultItemVo> =
//        listOf(
//            createResultItem(),
//            createResultItem(),
//            createResultItem(),
//            createResultItem(),
//            createResultItem(),
//            createResultItem()
//        )
//
//    private fun createResultItem(): SearchResultItemVo = SearchResultItemVo(
//        id = 0,
//        name = "Dom Americano",
//        productImageVo = createProductImageVo(),
//        description = "Italiano, Pizza, Risotto",
//        starsAndReviewsVo = StarsAndReviewsVo(
//            stars = 4,
//            reviews = 20,
//        ),
//        isFavourite = true,
//        distanceAndLocationVo = DistanceAndLocationVo(
//            timeToReceiveOrder = "10-20 min",
//            distanceFromPlace = "1.5km",
//        )
//    )
}
