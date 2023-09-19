<script setup>
    import { reactive, onMounted, watch } from 'vue'
    import { useRouter, useRoute } from 'vue-router'
    
    const router = useRouter()
    const route = useRoute()

    // 用户刷新的时候，保持菜单选中
    const selectKey = reactive([])
    // 如果为多级菜单，需要打开的父菜单
    const openKeys = reactive([])

    onMounted(() => {
        // 获取当前路由
        const path = route.path
        if('/home/create-product' === path) {
            selectKey.push('/home/product')
        }else if(path.startsWith('/home/device-detail')){
            selectKey.push('/home/device')
        }else if(path.startsWith('/home/group-detail')){
            selectKey.push('/home/group')
        }else {
            selectKey.push(path)
        }

        // 打开父菜单
        if(path != '/home/instance' && path.startsWith('/home')) {
            openKeys.push('/home')
        }
    })

    // 不刷新
    watch(() => route.path, newPath => {
        if(newPath.startsWith('/home/device-detail')){
            selectKey[0] = '/home/device'
        }else if(newPath.startsWith('/home/group-detail')){
            selectKey[0] = '/home/group'
        }else if(newPath === '/home/create-product') {
            selectKey[0] = '/home/product'
        }else {
            selectKey[0] = newPath
        }
    })

    // 路由跳转
    const go = (m) => {
        // 保证菜单选中
        selectKey.splice(0, 1, m.key)
        router.push(m.key)
    }

</script>
<template lang="">
    <a-layout-sider width="200" style="background-color: #f5f5f5; bottom: 0;">
        <a-menu
            @click="go"
            mode="inline"
            :openKeys="openKeys"
            :selectedKeys="selectKey"
            :style="{ borderRight: 0, backgroundColor: '#f5f5f5' }"
        >
            <a-menu-item key="/home/instance">
                <span>实例详情</span>
            </a-menu-item>
            <a-sub-menu key="/home">
                <template #title>
                    <span>设备管理</span>
                </template>
                <a-menu-item key="/home/product">产品</a-menu-item>
                <a-menu-item key="/home/device">设备</a-menu-item>
                <a-menu-item key="/home/group">分组</a-menu-item>
                <a-menu-item key="/home/simulator">设备模拟器</a-menu-item>
                <a-menu-item key="/home/device-dispatch">设备分发</a-menu-item>
                <a-menu-item key="/home/twins-engine">IoT孪生引擎</a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="sub2">
                <template #title>
                    <span>消息转发</span>
                </template>
                <a-menu-item key="/home/subcribe">服务端订阅</a-menu-item>
                <a-menu-item key="/home/wander-about">云产品流转</a-menu-item>
            </a-sub-menu>
        </a-menu>
        <div></div>
    </a-layout-sider>
</template>

<style lang="less">
@import '@/assets/global.less';
h3 {
    flex-direction: column;
}
</style>
