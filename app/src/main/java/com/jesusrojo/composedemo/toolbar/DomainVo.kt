package com.jesusrojo.composedemo.toolbar


sealed class ToolbarTypeVo {
    object Back : ToolbarTypeVo()
    object Close : ToolbarTypeVo()
    object Clear : ToolbarTypeVo()
}