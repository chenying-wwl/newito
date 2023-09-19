<script setup>
   import { HomeOutlined, GroupOutlined, DownOutlined, SearchOutlined } from '@ant-design/icons-vue'
</script>
<template>
    <div class="center">
        <div class="special">
            <home-outlined class="icon-left" />
            <span>工作台</span>
        </div>
        <div>
            <group-outlined class="icon-left"/>
            <span>工作台</span>
            <down-outlined class="icon-right"/>
        </div>
        <div class="special">
            <span>华东1（杭州）</span>
            <down-outlined />
        </div>
        <div>
            <a-input placeholder="搜索">
                <template #prefix>
                    <search-outlined class="color: var(--cb-color-text-tertiary,#999);"></search-outlined>
                </template>
            </a-input>
        </div>
    </div>
</template>

<style lang="less" scoped>
    .center {
        flex: 1;
        display: flex;
        padding: 0 2em;
        align-items: center;
        > div.special {
            background-color: var(--cb-color-button-tertiary-alt-bg,#f4f6f7);
            border-radius: 3px;
        }
        > div {
            height: 32px;
            padding: 0 12px;
            display: flex;
            align-items: center;
            margin-right: 2.2em;
            cursor: pointer;
            > .icon-left {
                margin-right: 0.6em;
            }
            > .icon-right {
                margin-left: 0.6em;
            }
            > span {
                word-break: normal;
                font-size: 12px;
                color: var(--cb-color-button-text-brand-secondary-text,#666);
            }
        }
        > div:last-of-type {
            flex: 1;
        }
    }
</style>