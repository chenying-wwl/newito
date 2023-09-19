<script setup>  
    import { ref } from 'vue'
    import { SearchOutlined, ClearOutlined } from '@ant-design/icons-vue'
    import useDevice from '@/hooks/useDevice'
    import { useRouter } from 'vue-router'
    import useTenant from '@/store/tenant.js'
    // import useDeviceStore from '@/store/device.js'
    import useProduct from '@/store/product.js'
    import useDeviceType from '@/store/deviceType.js'
    import SendCmdDialog from '@/components/device/SendCmdDialog.vue'

    import { storeToRefs } from 'pinia'
    
    const tenantStore = useTenant()
    // const deviceStore = useDeviceStore()
    const productStore = useProduct()
    const deviceTypeStore = useDeviceType()

    const router = useRouter()

    const sendRef = ref()

    const { tenants } = storeToRefs(tenantStore)
    // const { devices } = storeToRefs(deviceStore)
    const { products } = storeToRefs(productStore)
    const { deviceTypes } = storeToRefs(deviceTypeStore)

    const {data, columns, searchForm, total, flip, search,
        clear, formRef, actives } = useDevice()

    // 过滤器设备类型
    const filterDeviceTypes = id => {
        return deviceTypes.value.find(item => item.id === id).name
    }

     // 过滤器产品类型
     const filterProduct = id => {
        return products.value.find(item => item.id === id).name
    }

    const filterTenant = id => {
        return tenants.value.find(item => item.id === id).name
    }

    // 发指令
    const sendCmd = key => {
        sendRef.value.showModal(key)
    }

    // 查询接收的数据
    const showRecieveData = key => {
        router.push(`/home/receive-data/${key}`)
    }

    // 查询发送的数据
    const showSendData = key => {
        router.push(`/home/send-data/${key}`)
    }
</script>
<template>
    <a-form layout="inline" :model="searchForm" ref="formRef" autocomplete="off">
        <a-form-item name="name">
            <a-input v-model:value="searchForm.name" placeholder="请输入设备名称"></a-input>
        </a-form-item>
        <a-form-item name="deviceKey">
            <a-input v-model:value="searchForm.deviceKey" placeholder="请输入设备标识"></a-input>
        </a-form-item>
        <a-form-item name="deviceTypeId">
            <a-select
                v-model:value="searchForm.deviceTypeId"
                style="width: 170px"
                :options="deviceTypes.map(c => ({value: c.id, label: c.name}))"
            ></a-select>
        </a-form-item>
        <a-form-item name="active">
            <a-select
                v-model:value="searchForm.active"
                style="width: 170px"
                :options="actives.map(c => ({value: c.id, label: c.name}))"
            ></a-select>
        </a-form-item>
        <a-form-item>
            <a-space>
                <a-button @click="search" type="primary">
                    <template #icon><SearchOutlined /></template>
                    搜索
                </a-button>
                <a-button @click="clear(formRef)" type="danger">
                    <template #icon><clear-outlined /></template>
                    清空
                </a-button>
            </a-space>
        </a-form-item>
    </a-form>
    <a-table size="small" style="margin-top: 1em;" :columns="columns" :data-source="data" :pagination="false">
        <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'deviceTypeId'">
                {{ filterDeviceTypes(record.deviceTypeId) }}
            </template>
            <template v-if="column.key === 'productId'">
                {{ filterProduct(record.productId) }}
            </template>
            <template v-if="column.key === 'tenantId'">
                {{ filterTenant(record.tenantId) }}
            </template>
            <template v-if="column.key === 'parentId'">
                <template v-if="record.parentId === '0'">
                    无
                </template>
            </template>
            <template v-if="column.key === 'active'">
                <template v-if="record.active === 1">
                    <a-tag color="default">未激活</a-tag>
                </template>
                <template v-if="record.active === 2">
                    <a-tag color="#87d068">激活</a-tag>
                </template>
            </template>
            <template v-if="column.key === 'state'">
                <template v-if="record.state === true">
                    <a-tag color="#87d068">在线</a-tag>
                </template>
                <template v-if="record.state === false">
                    <a-tag color="default">离线</a-tag>
                </template>
            </template>
            <template v-if="column.key === 'action'">
                <a-button size="small" type="link" @click="showRecieveData(record.deviceKey)" class="action-btn">查询数据</a-button>
                <a-button size="small" type="link" @click="sendCmd(record.deviceKey)" class="action-btn cmd-btn">发指令</a-button>
                <a-button size="small" type="link" @click="showSendData(record.deviceKey)" class="action-btn cmd-btn">指令日志</a-button>
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
    <send-cmd-dialog ref="sendRef"></send-cmd-dialog>
</template>
<style lang="less" scoped>
.action-btn{
    font-size: 12px;
}
.cmd-btn {
    color: #f50;
}
</style>