<script setup>
    import useSendData from '@/hooks/useSendData'
    import { storeToRefs } from 'pinia'
    import { useRouter } from 'vue-router'

    import useTenant from '@/store/tenant.js'
    import useDeviceStore from '@/store/device.js'
    import useProduct from '@/store/product.js'

    const tenantStore = useTenant()
    const deviceStore = useDeviceStore()
    const productStore = useProduct()

    const { tenants } = storeToRefs(tenantStore)
    const { devices } = storeToRefs(deviceStore)
    const { products } = storeToRefs(productStore)

    const router = useRouter()

    const props = defineProps({
        id: String
    })

    const {data, columns, searchForm, total, flip, search, clear, formRef } = useSendData(props.id)

     // 过滤器产品类型
     const filterProduct = product_key => {
        return products.value.find(item => item.productKey === product_key).name
    }

    // 租户数据
    const filterTenant = id => {
        return tenants.value.find(item => item.id === id).name
    }

    // 设备数据
    const filterDevice = dv_key => {
        return devices.value.find(item => item.deviceKey === dv_key).name
    }
</script>
<template>
    <a-breadcrumb style="margin: 16px 0">
        <a-breadcrumb-item style="cursor: pointer;" @click="() => router.push('/home/device')">设备管理</a-breadcrumb-item>
        <a-breadcrumb-item>设备发送指令</a-breadcrumb-item>
    </a-breadcrumb>
    <a-table size="small" style="margin-top: 1em;" :columns="columns" :data-source="data" :pagination="false">
        <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'data'">
                {{ record.data }}
            </template>
            <template v-if="column.key === 'tenantId'">
                {{ filterTenant(record.tenantId) }}
            </template>
            <template v-if="column.key === 'productKey'">
                {{ filterProduct(record.productKey) }}
            </template>
            <template v-if="column.key === 'deviceKey'">
                {{ filterDevice(record.deviceKey) }}
            </template>
        </template>
    </a-table>
    <a-pagination 
        v-model:current="searchForm.page.current"
        :total="total"
        :show-size-changer="false"
        @change="flip"
        :hide-on-single-page="true"
        show-less-items
        size="small"
        style="margin-top: 1em; text-align: right;"/>
</template>
<style lang="less" scoped>
.action-btn{
    font-size: 12px;
}
</style>