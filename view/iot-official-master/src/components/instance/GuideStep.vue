<script setup>
    import { RightCircleOutlined } from '@ant-design/icons-vue'
</script>
<template lang="">
    <a-card title="欢迎使用物联网平台，您可以通过以下指引快速上手：" class="guide-steps">
        <a-card-grid style="width: 25%;" :hoverable="false" class="guide-step">
            <img src="../../assets/images/one.png" />
            <h3>设备接入</h3>
            <p>创建设备并接入平台</p>
            <right-circle-outlined class="next-icon"></right-circle-outlined>
        </a-card-grid>
        <a-card-grid style="width: 25%;" :hoverable="false" class="guide-step">
            <img src="../../assets/images/two.png" />
            <h3>消息转发</h3>
            <p>服务端订阅设备上报数据</p>
            <right-circle-outlined class="next-icon"></right-circle-outlined>
        </a-card-grid>
        <a-card-grid style="width: 25%;" :hoverable="false" class="guide-step">
            <img src="../../assets/images/three.png" />
            <h3>监控配置</h3>
            <p>配置监控指标和报警</p>
            <right-circle-outlined class="next-icon"></right-circle-outlined>
        </a-card-grid>
        <a-card-grid style="width: 25%;" :hoverable="false" class="guide-step">
            <img src="../../assets/images/four.png" />
            <h3>OTA 升级</h3>
            <p>远程升级或修复设备</p>
        </a-card-grid>
    </a-card>
</template>
<style lang="less" scoped>
@import '@/assets/global.less';

.guide-steps {
    padding: 0 1em 1em;
    .guide-step {
        display: flex;
        position: relative;
        height: 120px;
        flex-direction: column;
        justify-content: center;
        padding-left: 2em;
        img {
            top: 1em;
            height: 44px;
            width: 30px;
            position: absolute;
        }
        p {
            font-size: @sm-size;
            color: #888;
        }
        .next-icon {
            position: absolute;
            color: #888;
            font-size: 28px;
            top: 50%;
            right: -14px;
            transform: translate(0, -50%);
            background-color: #fff;
            z-index: 200;
        }
    }
}
</style>