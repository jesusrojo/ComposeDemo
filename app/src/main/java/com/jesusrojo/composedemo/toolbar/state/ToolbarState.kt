package com.jesusrojo.composedemo.toolbar.state

import com.jesusrojo.composedemo.toolbar.ToolbarTypeVo

//sealed class ToolbarState : BaseState() {
sealed class ToolbarState  {
    data class ShowTitleToolbar(val type: ToolbarTypeVo = ToolbarTypeVo.Back) : ToolbarState()
    object GoBack : ToolbarState()
    object ShowHomeToolbar : ToolbarState()
    object ShowHomeDeliveryManToolbar : ToolbarState()
    object HideHomeToolbar : ToolbarState()
    object ShowSearchToolbar : ToolbarState()
    data class ShowTitleWithFilterToolbar(val filterAction: (() -> Unit)? = null) : ToolbarState()
}
