<script setup>
  import { useRouter} from 'vue-router'

  const router = useRouter()

  const toLogin = () => {
    router.push('/login')
  } 

</script>

<template>
  <a-layout class="layout">
    <a-layout-header>
      <div class="logo" />
      <ul class="left-items">
        <li>产品</li>
        <li>解决方案</li>
        <li>文档与社区</li>
        <li>免费试用</li>
        <li>定价</li>
        <li>云市场</li>
        <li>合作伙伴</li>
        <li>支持与服务</li>
      </ul>
      <ul class="right-items">
        <li>控制台</li>
        <li @click="toLogin">登录/注册</li>
      </ul>
    </a-layout-header>
  </a-layout>
</template>

<style lang="less" scoped>
@import '@/assets/global.less';

.ant-layout-header {
  background-color: #fff;
  box-shadow: inset 0 -1px 0 0 rgba(61, 61, 61, .15);
  display: flex;
  padding-right: 0;
  .left-items, .right-items {
    display: flex;
    list-style: none;
    margin-bottom: 0;
    > li {
      padding: 0 1em;
      cursor: default;
      &:hover {
        color: @primary-color;
      }
    }
  }
  .left-items {
    margin-right: auto;
  }
  .right-items {
    > li {
      cursor: default;
      &:last-child {
        background-color: @primary-color;
        color: #fff;
        cursor: pointer;
      }
    }
  }
}
</style>
