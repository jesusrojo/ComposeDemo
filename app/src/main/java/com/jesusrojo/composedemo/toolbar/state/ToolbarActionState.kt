package com.jesusrojo.composedemo.toolbar.state


//sealed class ToolbarActionState : BaseState() {
sealed class ToolbarActionState() {
    object OnHomeSearchPressed : ToolbarActionState()
    object OnIconLogoPressed : ToolbarActionState()
    object OnNotificationsPressed : ToolbarActionState()
    data class OnSearchFragmentSearchPressed(val searchText: String) : ToolbarActionState()
  //  data class ShowSearchResults(val items: List<SearchResultItemVo>) : ToolbarActionState()
    object ResetSearchScreen : ToolbarActionState()
}
