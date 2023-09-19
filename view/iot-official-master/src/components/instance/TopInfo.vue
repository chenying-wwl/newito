<script setup>
import { CheckCircleFilled } from '@ant-design/icons-vue'
</script>
<template lang="">
    <div class="top-info">
        <div>
            <a-avatar :size="84" src="https://img.alicdn.com/imgextra/i1/O1CN01LTRBVo1RiueAFEXvm_!!6000000002146-2-tps-704-704.png">
            </a-avatar>
            <div>
                <h3>公共实例</h3>
                <p style="font-size: 12px;"> <check-circle-filled /> 已开通</p>
            </div>
        </div>
        <!-- <a-space> -->
            <!-- <a-button class="btn">升级企业版</a-button> -->
            <!-- <a-button class="btn special-btn">查看开发配置</a-button> -->
        <!-- </a-space> -->
    </div>
</template>

<style lang="less" scoped>
@import '@/assets/global.less';

.top-info {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 1em;
    > div:first-of-type {
        display: flex;
        align-items: center;
        color: @link-color;
        > div {
            margin-left: 1.5em;
        }
    }
    .btn {
        font-size: 12px;
    }
    .special-btn {
        background-color: @link-color;
        color: #fff;
    }
}
</style>