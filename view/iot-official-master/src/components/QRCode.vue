<script setup>

</script>
<template lang="">
    <a-card-grid :hoverable="false" class="qr-code">
        <div class="scan-code-title">关注公众号</div>
        <a-image
            :preview="false"
            :width="214"
            src="http://8.142.142.111/iot-offical/code.png"
        />
        <p style="font-size: 12px;">
            打开微信公众号扫描登录
        </p>
    </a-card-grid>
</template>

<style lang="less" scoped>
    .qr-code {
        width: 45% !important;
        text-align: center;
        box-shadow: none !important;
        padding-bottom: 0 !important; 
    }
</style>