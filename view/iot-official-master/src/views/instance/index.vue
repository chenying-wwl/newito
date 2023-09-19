<script setup>
  import TopInfo from '@/components/instance/TopInfo.vue'
  import GuideStep from '@/components/instance/GuideStep.vue'
  import SummaryInfo from '@/components/instance/SummaryInfo.vue'
  import RealTimeData from '@/components/instance/RealTimeData.vue'

</script>
<template lang="">
    <div style="padding: 0 24px 24px;">
        <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>物联网平台</a-breadcrumb-item>
            <a-breadcrumb-item>公共实例</a-breadcrumb-item>
        </a-breadcrumb>
        <a-layout-content :style="{ padding: '10px', margin: 0, minHeight: '480px' }">
            <top-info></top-info>
            <guide-step></guide-step>
            <summary-info></summary-info>
            <real-time-data></real-time-data>
        </a-layout-content>
    </div>
</template>

<style lang="less" scoped>

</style>
