<script setup>
    import { MenuOutlined } from '@ant-design/icons-vue'
    import HomeHeaderCenter from './HomeHeaderCenter.vue'
    import HomeHeaderRight from './HomeHeaderRight.vue'

</script>
<template lang="">
    <div class="top">
        <div class="header">
            <div class="menu-bar">
                <menu-outlined style="color: #fff; font-size: 22px;"></menu-outlined>
            </div>
            <div class="home-logo"/>
            <home-header-center></home-header-center>
            <home-header-right></home-header-right>
        </div>
    </div>
</template>

<style lang="less" scoped>
@import '@/assets/global.less';
.top {
    height: 50px;
    font-size: 12px;
    line-height: 1.5;
    -webkit-font-smoothing: antialiased;
    font-family: var(--cb-typo-font-family-base,-apple-system,BlinkMacSystemFont,"PingFang SC","Helvetica Neue",Helvetica,Arial,sans-serif);
    background-color: var(--cb-color-bg-primary,#fff);
    position: fixed;
    top: 0px;
    right: 0px;
    left: 0px;
    z-index: 999;
    box-shadow: 0 2px 4px 0 var(--cb-color-shadow,rgba(0,0,0,0.16));
    .header {
        background-color: #fff !important;
        height: 50px;
        box-shadow: 0 2px 4px 0 #e6e6e6;
        display: flex;
        line-height: 50px;
        > .menu-bar {
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: @primary-color;
            width: 50px;
            margin-right: 1em;
        }
    }
}

</style>