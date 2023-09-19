<script setup>
    import { ref, onMounted, reactive } from 'vue'
    import http from '@/utils/http.js'
    import useTenant from '@/store/tenant'
    import { storeToRefs } from 'pinia'

    const tenantStore = useTenant()
    const { tenantId } = storeToRefs(tenantStore)

    // radio的样式
    const radioStyle = reactive({
        display: 'flex',
        height: '30px',
        lineHeight: '30px',
    })

    const groups = ref([])
    
    const visible = ref(false)
    const groupId = ref('')

    onMounted(async () => {
        // 获取所有的分组
        const r = await http.get(`/tenant/group/list?tenantId=${tenantId.value}`)
        groups.value = r.data.records
    })

    const showModal = () => {
        visible.value = true
    }

    defineExpose({
        showModal
    })

</script>
<template>
    <a-modal
        v-model:visible="visible"
        title="选择设备分组"
        :mask-closable="false"
        okText="确认"
        cancelText="取消">
        <a-radio-group v-model:value="groupId">
            <a-radio :style="radioStyle" v-for="g in groups" :value="g.id" :key="g.id">{{ g.groupName }}</a-radio>
        </a-radio-group>
    </a-modal>
</template>
<style lang="less" scoped>
    
</style>