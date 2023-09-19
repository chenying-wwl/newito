import { useRouter } from 'vue-router'

export default function() {
    const router = useRouter()

     // 面包屑导航条，到产品页面
     const gotoProduct = () => {
        router.push('/home/product')
    } 

    // 面包屑导航条，到设备页面
    const gotoDevice = () => {
        router.push('/home/device')
    }

    // 面包屑导航条，到设备页面
    const gotoGroup = () => {
        router.push('/home/group')
    }

    return {
        gotoProduct,
        gotoDevice,
        gotoGroup
    }
}