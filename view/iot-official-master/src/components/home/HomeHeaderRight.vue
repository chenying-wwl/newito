<script setup>
    import {  AppstoreOutlined, FundOutlined, AlertOutlined, ShoppingCartOutlined, 
       BulbOutlined, QuestionCircleOutlined } from '@ant-design/icons-vue'
    import useTenant from '@/store/tenant'
    import { storeToRefs } from 'pinia'
    import useLogin from '@/hooks/useLogin.js'

    const tenantStore = useTenant()
    const { name } = storeToRefs(tenantStore)

    const { logout } = useLogin()

</script>
<template>
    <ul class="right-items">
        <li>费用</li>
        <li>ICP备案</li>
        <li>企业</li>
        <li>支持</li>
        <li>工单</li>
        <li>
            <appstore-outlined class="icon"></appstore-outlined>
        </li>
        <li>
            <fund-outlined class="icon"></fund-outlined>
        </li>
        <li>
            <alert-outlined class="icon"></alert-outlined>
        </li>
        <li>
            <shopping-cart-outlined class="icon"></shopping-cart-outlined>
        </li>
        <li>
            <bulb-outlined class="icon"></bulb-outlined>
        </li>
        <li>
            <question-circle-outlined class="icon"></question-circle-outlined>
        </li>
        <li>
            简体
        </li>
        <li>
            <div>
                <span>{{ name }}</span>
                <span>主账号</span>
            </div>
            <a-dropdown>
                <a class="ant-dropdown-link" @click.prevent>
                    <a-avatar src="https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg" />
                </a>
                <template #overlay>
                    <a-menu>
                        <a-menu-item>
                            <a-button type="link" @click="logout">退出账户</a-button>
                        </a-menu-item>
                    </a-menu>
                </template>
            </a-dropdown>
            
        </li>
    </ul> 
</template>

<style lang="less" scoped>
    ul.right-items {
        display: flex;
        margin: 0;
        > li {
            padding: 0 10px;
            font-size: 12px;
            color: var(--cb-color-button-text-brand-secondary-text,#666);
            > .icon {
                font-size: 14px;
            }
        }
        > li:last-of-type {
            display: flex;
            align-items: center;
            > div {
                display: flex;
                flex-direction: column;
                justify-content: center;
                margin-right: 1em;
                > span {
                    line-height: 12px;
                    text-align: right;
                }
                > span:first-of-type {
                    margin-bottom: 4px;
                }
                > span:last-of-type {
                    color: var(--cb-color-text-tertiary,#999);
                }
            }
            
        }
    }
</style>