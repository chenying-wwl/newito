<script setup>
    import { onMounted, ref } from 'vue'
    import http from '@/utils/http.js'
    import _ from 'lodash'
    import useTenant from '@/store/tenant'
    import { storeToRefs } from 'pinia'

    const tenantStore = useTenant()
    const { tenantId } = storeToRefs(tenantStore)
    const dispalyModels = ref([])

    const props = defineProps({
        deviceTypeId: String,
        deviceKey: String
    })

    const queryThenRenderDeviceModel = async () => {
        // 设备的物体模型
        const r1 = await http.get(`/tenant/device-model/list/${props.deviceKey}`)
        
        // 根据设备类型查询物模型，即所有的物模型
        const r2 = await http.get(`/tenant/model-template/list/${props.deviceTypeId}`)

        const deviceModels = r1.data
        const allModels = r2.data

        // 设备物模型 与 所有物模型取交集
        const r = _.intersectionWith(r1.data,  r2.data, (f, l) => f.templateId === l.id)

        // 获取所有的id
        const ids = r.map(item => item.templateId)

        // 给设备的物模型添加 bind 属性，以便前端渲染
        deviceModels.forEach(item => item.bind = true)

        // 所有的物模型中不包含已有物模型的数据
        const excludeModels = allModels.filter(item => ids.indexOf(item.id) === -1)

        excludeModels.forEach(item => item.bind = false)

        dispalyModels.value = [...deviceModels, ...excludeModels]
    }

    // 渲染物模型信息
    onMounted(async () => {
        queryThenRenderDeviceModel()
    })

    // 绑定设备物模型
    const bindDeviceModel = async (propertyName, modelType, id, description) => {
        const r = await http.post(`/tenant/device-model/add`, {
            propertyName,
            modelType,
            templateId: id,
            description,
            tenantId: tenantId.value,
            deviceKey: props.deviceKey
        })
        if(r.code === 200) {
            queryThenRenderDeviceModel()
        }
    }

    const unbindDeviceModel = async id => {
        const r = await http.delete(`/tenant/device-model/unbind/${id}`)
        if(r.code === 200) {
            queryThenRenderDeviceModel()
        } 
    }

</script>
<template>
    <a-row :gutter="10">
        <a-col v-for="m in dispalyModels" :span="4" class="mb-dot6" :key="m.id">
            <template v-if="m.bind">
                <a-card class="model" @click="unbindDeviceModel(m.id)">
                    <p>{{ m.description ?? '数据返回格式有问题' }}</p>
                    <a-tag class="ck" color="green">已绑定</a-tag>
                </a-card>
            </template>
            <template v-else>
                <a-card class="model" @click="bindDeviceModel(m.propertyName, m.modelType, m.id, m.description)">
                    <p>{{ m.description ?? '数据返回格式有问题' }}</p>
                    <a-tag class="ck" color="orange">未绑定</a-tag>
                </a-card>
            </template>
        </a-col>
    </a-row>
</template>
<style lang="less" scoped>
    .mb-dot6 {
        margin-bottom: 0.6em;
    }
    .ck {
        position: absolute;
        bottom: 0.3em;
        right: 0.3em;
    }
    .model {
        position: relative;
        cursor: pointer;
        height: 90px;
    }
</style>