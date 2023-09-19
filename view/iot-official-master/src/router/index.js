import { createRouter, createWebHashHistory } from 'vue-router'
import { storeToRefs } from 'pinia'
import useTenant from '@/store/tenant'
import useProduct from '@/store/product'

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/login',
            component: () => import('@/views/login/index.vue')
        },
        {
            path: '/register',
            component: () => import('@/views/register/index.vue')
        },
        {
            path: '/animate',
            component: () => import('@/views/animate/index.vue')
        },
        {
            path: '/home',
            component: () => import('@/views/home/index.vue'),
            redirect: '/home/instance',
            children: [
                {
                    path: '/home/instance',
                    component: () => import('@/views/instance/index.vue')
                },
                {
                    path: '/home/product',
                    component: () => import('@/views/product/index.vue')
                },
                {
                    path: '/home/device',
                    component: () => import('@/views/device/index.vue')
                },
                {
                    path: '/home/group',
                    component: () => import('@/views/group/index.vue')
                },
                {
                    path: '/home/create-product',
                    component: () => import('@/views/create-product/index.vue')
                },
                {
                    path: '/home/device-detail/:id',
                    component: () => import('@/views/device-detail/index.vue'),
                    props: true
                },
                {
                    path: '/home/group-detail/:id',
                    component: () => import('@/views/group-detail/index.vue'),
                    props: true
                },
                {
                    path: '/home/device-dispatch',
                    component: () => import('@/views/device-dispatch/index.vue')
                },
                {
                    path: '/home/simulator',
                    component: () => import('@/views/simulator/index.vue')
                },
                {
                    path: '/home/twins-engine',
                    component: () => import('@/views/twins-engine/index.vue')
                },
                {
                    path: '/home/subcribe',
                    component: () => import('@/views/subcribe/index.vue')
                },
                {
                    path: '/home/wander-about',
                    component: () => import('@/views/wander-about/index.vue')
                }
            ]
        },
        {
            path: '/',
            redirect: '/home'
        },
        {
            path: '/*',
            redirect: '/'
        }
    ]
})

router.beforeEach((to, from, next) => {
    // 只有放到这里，pinia的持久化才不会失效
    const tenantStore = useTenant()
    const { name, tenantId, token } = storeToRefs(tenantStore)
    if(to.path === '/login' || to.path === '/register') {
        next()
    } else if(name.value && tenantId.value && token.value){
        const productStore = useProduct()
        const { products } = storeToRefs(productStore)
        // 如果产品没有值，就取获取
        if(products.value.length === 0) {
            productStore.getAllProducts()
        }
        next()
    }else {
        next('/login')
    }
})

export default router