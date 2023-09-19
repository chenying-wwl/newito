<script setup>
    import { HddOutlined, CloudSyncOutlined } from '@ant-design/icons-vue'
    import RealTimeRunUnit from './RealTimeRunUnit.vue';
    import { use } from "echarts/core"
    import { CanvasRenderer } from "echarts/renderers"
    import { TitleComponent, TooltipComponent, GridComponent, LegendComponent } from 'echarts/components'
    import { LineChart } from 'echarts/charts'
    import { UniversalTransition } from 'echarts/features'
    import VChart, { THEME_KEY } from "vue-echarts"
    import { ref, onMounted, onUnmounted } from "vue"

    use([
        CanvasRenderer,
        LineChart,
        TitleComponent,
        TooltipComponent,
        LegendComponent,
        GridComponent,
        UniversalTransition
    ])

    const randomData = () => {
        now = new Date(+now + oneDay)
        value = value + Math.random() * 21 - 10;
        return {
            name: now.toString(),
            value: [
                [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
                Math.round(value)
            ]
        }
    }

    const percent1 = ref(0) 
    const percent2 = ref(0) 
        
    const data = ref([])
    let now = new Date(1997, 1, 1);
    let oneDay = 24 * 3600 * 1000
    let value = Math.random() * 1000;

    for (var i = 0; i < 1000; i++) {
        data.value.push(randomData());
    }

    let timer = null

    onMounted(() => {
        timer = setInterval(function () {
            for (var i = 0; i < 5; i++) {
                data.value.shift();
                data.value.push(randomData());
            }
            percent1.value = Math.floor(Math.random() * 98) + 1
            percent2.value = Math.floor(Math.random() * 98) + 1
        }, 1000);
    })

    onUnmounted(() => {
        clearInterval(timer)
    })

    const option = ref({
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
            params = params[0];
            var date = new Date(params.name);
            return (
                date.getDate() +
                '/' +
                (date.getMonth() + 1) +
                '/' +
                date.getFullYear() +
                ' : ' +
                params.value[1]
            );
            },
            axisPointer: {
            animation: false
            }
        },
        xAxis: {
            type: 'time',
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            }
        },
        series: [
            {
                name: 'Fake Data',
                type: 'line',
                showSymbol: false,
                data: data
            }
        ],
        grid: [
            {
                bottom: 20,
                left: 50,
                right: 45,
                top: 20
            }
        ]
    })
</script>
<template>
    <h3 style="margin: 1em 0;">实时运行数据</h3>
    <!--
    <a-row :gutter="10" style="margin-bottom: 1em;">
        <a-col :span="6">
            <a-card>
                <template #title>
                    <hdd-outlined /> 实例在线设备
                </template>
                <template #extra><a href="#">升级企业版</a></template>
                <div class="summary">
                    <div class="main-info">
                        <p>
                            <span>已添加 0</span>
                        </p>
                        <a-progress :percent="0" :show-info="false"/>
                        <p>
                            <span>0</span>
                            <span>500</span>
                        </p>
                    </div>
                    <p>
                        用量 0 %（最多在线 50 个）
                    </p>
                </div>    
            </a-card>
        </a-col>
        <a-col :span="18">
            <real-time-run-unit>
                <template #title>
                    <span>在线设备数</span>
                    <a-button type="link" style="font-size: 12px;">报警配置</a-button>
                </template>
            </real-time-run-unit>
        </a-col>
    </a-row>
    -->
    <a-row :gutter="10" style="margin-bottom: 1em;">
        <a-col :span="6">
            <a-card>
                <template #title>
                    <cloud-sync-outlined /> 实时消息上下行 TPS
                </template>
                <!-- <template #extra><a href="#">升级企业版</a></template> -->
                <div style="text-align: center;">
                    <a-progress type="circle" :percent="percent1" />
                    <p style="font-size: 12px; color: #888; margin-top: 1em;">
                        用量 {{percent1}}% (上限 5 条/秒)
                    </p>
                </div>
            </a-card>
        </a-col>
        <a-col :span="18">
            <real-time-run-unit>
                <template #title>
                    <span>消息上下行TPS</span>
                    <!-- <a-button type="link" style="font-size: 12px;">报警配置</a-button> -->
                </template>
                <template #chart>
                    <v-chart style="height: 180px;" :option="option" />
                </template>
            </real-time-run-unit>
            
        </a-col>
    </a-row>
    <a-row :gutter="10">
        <a-col :span="6">
            <a-card>
                <template #title>
                    <cloud-sync-outlined /> 实时消息转发 TPS
                </template>
                <!-- <template #extra><a href="#">升级企业版</a></template> -->
                <div style="text-align: center;">
                    <a-progress type="circle" :percent="percent2" />
                    <p style="font-size: 12px; color: #888; margin-top: 1em;">
                        用量 {{percent2}} % (上限 5 条/秒)
                    </p>
                </div>    
            </a-card>
        </a-col>
        <a-col :span="18">
            <real-time-run-unit>
                <template #title>
                    <span>实时消息转发 TPS</span>
                    <!-- <a-button type="link" style="font-size: 12px;">报警配置</a-button> -->
                </template>
                <template #chart>
                    <v-chart style="height: 180px;" :option="option" />
                </template>
            </real-time-run-unit>
        </a-col>
    </a-row>
</template>
<style lang="less" scoped>
@import '@/assets/global.less';

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