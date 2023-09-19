<script setup>
    import { HddOutlined, CloudSyncOutlined } from '@ant-design/icons-vue'
    import http from '@/utils/http.js'
    import useBreadcrumb from '@/hooks/useBreadcrumb'
    import { onMounted, reactive } from 'vue'
    import useTenant from '@/store/tenant'
    import { storeToRefs } from 'pinia'

    const { gotoDevice } = useBreadcrumb()

    const tenantStore = useTenant()
    const { tenantId } = storeToRefs(tenantStore)

    const deviceInfo = reactive({
        total: 0,
        rate: 0
    })

    onMounted(async () => {
        const r = await http.get(`/tenant/device/count/${tenantId.value}/0`)
        deviceInfo.total = r.data.allCount
        deviceInfo.rate = Math.round((deviceInfo.total * 100 / 500))
    })

</script>

<template>
    <a-row :gutter="10" style="margin-top: 1em;">
        <a-col :span="6">
            <a-card>
                <template #title>
                    <hdd-outlined /> 实例基本信息
                </template>
                <div class="base-info">
                    <div>实例 ID</div>
                    <div>iot-06z00ainbaxov6z</div>
                </div>
                <div class="base-info">
                    <div>备注名称</div>
                    <div>公共实例</div>
                </div>
                <div class="base-info">
                    <div>开通时间</div>
                    <div>2023/05/08 23:42:51</div>
                </div>
                <div class="base-info" style="margin-bottom: 0;">
                    <div>资源组 ID</div>
                    <div>rg-acfmv3ulyb4uz2y</div>
                </div>
            </a-card>
        </a-col>
        <a-col :span="6">
            <a-card>
                <template #title>
                    <cloud-sync-outlined /> 实例设备数
                </template>
                <template #extra><a-button type="link" @click="gotoDevice">查看</a-button></template>
                <div class="summary">
                    <div class="main-info">
                        <p>
                            <span>已添加 {{ deviceInfo.total }}</span>
                            <!-- <a-button class="btn" type="link">升级企业版</a-button> -->
                        </p>
                        <a-progress :percent="deviceInfo.rate" :show-info="false"/>
                        <p>
                            <span>{{ deviceInfo.total }}</span>
                            <span>500</span>
                        </p>
                    </div>
                    <p>
                        用量 {{ deviceInfo.rate }}%（共可添加 500）
                    </p>
                </div>    
            </a-card>
        </a-col>
    </a-row>
</template>

<style lang="less" scoped>
@import '@/assets/global.less';
.base-info {
    display: flex;
    font-size: @sm-size;
    color: #888;
    text-align: left;
    margin-bottom: 1.5em;
    > div:first-of-type {
        min-width: 100px;
        text-overflow: ellipsis;
    }
}
.summary {
    display: flex;
    height: 129px;
    flex-direction: column;
    > .main-info {
        flex: 1;
        > p {
            display: flex;
            justify-content: space-between;
            align-items: center;
            .btn {
                font-size: @sm-size;
            }
        }
        > P:last-of-type {
            color: #888;
            font-size: @sm-size;
        }
    }
    > p:first-of-type {
        color: #888;
        font-size: @sm-size;
    }
}
</style>