<script setup>
    import { ref } from 'vue'
    import { SearchOutlined, RedoOutlined, QuestionCircleFilled } from '@ant-design/icons-vue'
    import { storeToRefs } from 'pinia'
    import useProduct from '@/store/product'
    import useDevice from '@/hooks/useDevice.js'
    import AddDeviceDialog from './AddDeviceDialog.vue'
    import AddDeviceToGroupDialog from './AddDeviceToGroupDialog.vue'
    import SwitchDeviceDialog from '../SwitchDeviceDialog.vue'

    const { columns, data, flip, total, current, showDetail, deleteDevice, 
        productId, deviceNumbers, refreshDeviceInfo } = useDevice()

    const addDg = ref()
    const addToGroupDg = ref()
    const switchDeviceDg = ref()
    const productStore = useProduct()
    const { products } = productStore

    const productsWithAll = [{id: 0, name: '所有产品'}, ...products]

    const addDevice = () => {
        addDg.value.showModal()
    }

    const switchDevice = (name, key) => {
        switchDeviceDg.value.showModal(name, key)
    }

    // const showDeviceToGroup = () => {
    //     addToGroupDg.value.showModal()
    // }
</script>
<template>
    <div class="summary">
        <a-space :size="60">
            <a-select
                v-model:value="productId"
                style="width: 200px"
                :options="productsWithAll.map(p => ({value: p.id, label: p.name}))"
            >
            </a-select>
            <a-statistic :value="deviceNumbers.total">
                <template #title>
                    设备总数 <question-circle-filled />
                </template>
            </a-statistic>
            <a-statistic :value="deviceNumbers.active">
                <template #title>
                    <i class="dot blue-dot"></i> 激活设备 <question-circle-filled />
                </template>
            </a-statistic>
            <a-statistic :value="deviceNumbers.online">
                <template #title>
                    <i class="dot green-dot"></i> 当前在线 <question-circle-filled />
                </template>
            </a-statistic>
        </a-space>
        <a-button>
            <redo-outlined @click="refreshDeviceInfo"/>
        </a-button>
    </div>
    <a-space style="margin-bottom: 1em;">
        <a-button @click="addDevice" type="primary">添加设备</a-button>
        <!-- <a-input placeholder="请输入设备名称查询">
            <template #suffix>
                <search-outlined />
            </template>
        </a-input> -->
    </a-space>
    <a-table size="small" :columns="columns" :data-source="data" :pagination="false">
        <template #emptyText>
            <a-empty />
        </template>
        <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'name'">
                <a-button size="small" type="link" @click="showDetail(record.id)" class="action-btn">{{ record.name }}</a-button>
            </template>
            <template v-if="column.key === 'deviceSwitch'">
                <!-- 1.网关，可以接
                     2.传感器 
                     3.继电器；
                     4.摄像头

                     网关、摄像头: 可以接收指令，可上报数据
                     传感器：只能上报数据；
                     继电器: 开关，可以接收指令，不上报数据；
                -->
                <template v-if="record.deviceType === '1'">
                    <a-tag color="cyan">网关设备</a-tag>
                </template>
                <template v-else-if="record.deviceType === '2'">
                    <a-tag color="cyan">传感器</a-tag>
                </template>
                <template v-else-if="record.deviceType === '3'">
                    <a-button type="link" danger @click="switchDevice(record.name, record.deviceKey)">发送指令</a-button>
                    <!-- <a-switch :checked="false"
                        size="small"
                        @change="switchDevice(record.name, record.deviceKey)"
                        checked-children="开"
                        un-checked-children="关" />                                                                                      -->
                </template>
                <template v-else>
                    <a-tag color="cyan">摄像头</a-tag>
                </template>
            </template>
            <template v-if="column.key === 'active'">
                <a-tag v-if="record.online" color="#87d068">在线</a-tag>
                <a-tag v-else-if="record.active === 2" color="#108ee9">激活</a-tag>
                <a-tag v-else color="#f50">未激活</a-tag>
            </template>
            <template v-else-if="column.key === 'action'">
                <a-button size="small" type="link" @click="showDetail(record.id)" class="action-btn">详情</a-button>
                <!-- <a-button size="small" type="link" @click="showDeviceToGroup(record.id)" class="action-btn">添加设备到分组</a-button> -->
                <a-button size="small" type="link" @click="deleteDevice(record.id)" class="action-btn">删除</a-button>
            </template>
        </template>
    </a-table>
    <!-- <a-pagination 
        v-model:current="current" 
        :page-size="5"
        size="small"
        :total="total"
        show-less-items
        @change="flip"
        :hide-on-single-page="true"
        style="margin-top: 1em; text-align: right;"/> -->
    <add-device-dialog ref="addDg" :products="products" @refresh="flip(current)"></add-device-dialog>
    <add-device-to-group-dialog ref="addToGroupDg" ></add-device-to-group-dialog>
    <switch-device-dialog ref="switchDeviceDg"></switch-device-dialog>
</template>
<style lang="less" scoped>
@import '@/assets/global.less';
.action-btn{
    font-size: @sm-size;
}
.summary {
    margin: 1em 0;
    display: flex;
    justify-content: space-between;
    .dot {
        height: 0.5em; 
        width: 0.5em;
        display: inline-block;
        border-radius: 50%;
        position: relative;
        bottom: 1px;
    }
    .blue-dot {
        border: 1px solid blue;
        background-color: blue;
    }
    .green-dot {
        border: 1px solid green;
        background-color: green;
    }
} 
</style>