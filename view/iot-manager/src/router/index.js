import { createRouter, createWebHashHistory } from 'vue-router'
import Cookies from 'js-cookie'
import useIndustry from '@/store/industry.js'
import useDeviceType from '@/store/deviceType.js'
import useArea from '@/store/area.js'
import useTenant from '@/store/tenant.js'
import useDevice from '@/store/device.js'
import useProduct from '@/store/product.js'
import useAdmin from '@/store/admin.js'

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/login',
            component: () => import('@/views/login/index.vue')
        },
        {
            path: '/home',
            component: () => import('@/views/home/index.vue'),
            redirect: '/home/area',
            children: [
                {
                    path: '/home/admin',
                    component: () => import('@/views/admin/admin.vue')
                },
                {
                    path: '/home/area',
                    component: () => import('@/views/area/index.vue')
                },
                {
                    path: '/home/tenant-examine',
                    component: () => import('@/views/tenant-examine/index.vue')
                },
                {
                    path: '/home/tenant-manager',
                    component: () => import('@/views/tenant-manager/index.vue')
                },
                {
                    path: '/home/industry',
                    component: () => import('@/views/industry/index.vue')
                },
                {
                    path: '/home/device-type',
                    component: () => import('@/views/device-type/index.vue')
                },
                {
                    path: '/home/model',
                    component: () => import('@/views/model/index.vue')
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
                    path: '/home/receive-data/:id',
                    component: () => import('@/views/receive-data/index.vue'),
                    props: true
                },
                {
                    path: '/home/send-data/:id',
                    component: () => import('@/views/send-data/index.vue'),
                    props: true
                } 
            ]
        },
        {
            path: '/',
            redirect: '/home'
        },
        {
            path: '/*',
            redirect: '/login'
        }
    ]
})

router.beforeEach((to, from, next) => {
    if(to.path === '/login') {
        next()
    }else if(to.path === '/login' || Cookies.get('token')) {
        const areaStore = useArea()
        if(!areaStore.areas || areaStore.areas.length === 0) {
            areaStore.getAllArea()
        }
        // 如果是到设备类型页面，获取设备行业
        if(to.path === '/home/device-type') {
            const industryStore = useIndustry()
            industryStore.getAllIndustry()
        }
        if(to.path === '/home/model') {
            const deviceTypeStore = useDeviceType()
            deviceTypeStore.getAllDeviceTypes()
        }
        if(to.path === '/home/product') {
            const tenantStore = useTenant()
            tenantStore.getAllTenant()
        }
        if(to.path === '/home/device') {
            const deviceTypeStore = useDeviceType()
            const productStore = useProduct()
            const tenantStore = useTenant()
            const deviceStore = useDevice()

            productStore.getAllProduct()
            deviceTypeStore.getAllDeviceTypes()
            tenantStore.getAllTenant()
            deviceStore.getAllDevice()
        }
        if(to.path.startsWith('/home/receive-data')) {
            const productStore = useProduct()
            const tenantStore = useTenant()
            const deviceStore = useDevice()

            productStore.getAllProduct()
            tenantStore.getAllTenant()
            deviceStore.getAllDevice()
        }
        if(to.path.startsWith('/home/send-data')) {
            const productStore = useProduct()
            const tenantStore = useTenant()
            const deviceStore = useDevice()

            productStore.getAllProduct()
            tenantStore.getAllTenant()
            deviceStore.getAllDevice()
        }
        next()
    }else {
        next('/login')
    }
})

export default router
