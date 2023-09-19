<script setup>
    import { onMounted, reactive } from 'vue'
    import { useRouter, useRoute } from 'vue-router'
    import { MenuUnfoldOutlined, MenuFoldOutlined, RadarChartOutlined, UserOutlined,
           AuditOutlined, MenuOutlined, PrinterOutlined, LayoutOutlined, 
        ContainerOutlined, ControlOutlined} from '@ant-design/icons-vue'
    import Cookies from 'js-cookie'

    const route = useRoute()
    const router = useRouter()

    const selectedKey = reactive([])

    // 路由跳转
    const go = (m) => {
        // 保证菜单选中
        selectedKey[0] = m.key
        router.push(m.key)
    }

    const logout = () => {
        Cookies.remove('token')
        router.push('/login')
    }

    onMounted(() => {
        // 获取当前路由
        const path = route.path
        if(path.startsWith('/home/receive-data') || path.startsWith('/home/send-data')){
            selectedKey.push('/home/device')
        }else {
            selectedKey.push(path)
        }
    })
</script>
<template lang="">
    <a-layout style="min-height:100vh">
        <a-layout-sider :trigger="null">
            <div class="logo" />
            <a-menu
                @click="go"
                v-model:selectedKeys="selectedKey"
                theme="dark"
                mode="inline">
                <a-menu-item key="/home/area">
                    <radar-chart-outlined />
                    <span>行政区管理</span>
                </a-menu-item>
                <a-menu-item key="/home/tenant-examine">
                    <audit-outlined />
                    <span>租户审核</span>
                </a-menu-item>
                <a-menu-item key="/home/tenant-manager">
                    <user-outlined/>
                    <span>租户管理</span>
                </a-menu-item>
                <a-menu-item key="/home/industry">
                    <menu-outlined />
                    <span>行业管理</span>
                </a-menu-item>
                <a-menu-item key="/home/device-type">
                    <printer-outlined />
                    <span>设备类型管理</span>
                </a-menu-item>
                <a-menu-item key="/home/model">
                    <layout-outlined />
                    <span>物模型管理</span>
                </a-menu-item>
                <a-menu-item key="/home/product">
                    <container-outlined />
                    <span>产品管理</span>
                </a-menu-item>
                <a-menu-item key="/home/device">
                    <control-outlined />
                    <span>设备管理</span>
                </a-menu-item>
            </a-menu>
        </a-layout-sider>
        <a-layout>
            <a-layout-header style="background: #fff; padding-right: 30px;">
                <div style="float: right;">
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
                </div>
            </a-layout-header>
            <a-layout-content class="content">
                <router-view></router-view>
            </a-layout-content>
        </a-layout>
    </a-layout>
</template>

<style lang="less" scoped>
.trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}
.trigger:hover {
  color: #1890ff;
}
.content {
    margin: 10px;
    padding: 24px;
    background-color: #fff;
}
</style>