<script setup>
  import { DownOutlined } from '@ant-design/icons-vue'
  
  import useGoIndex from '@/hooks/useGoIndex'
  const { goIndex } = useGoIndex()
</script>
<template>
  <a-layout-header>
    <div class="logo" @click="goIndex"/>
    <p>
        <slot name="title"></slot>
    </p>
    <p>
      中国站
      <a-dropdown>
        <a class="ant-dropdown-link" @click.prevent>
          <down-outlined />
        </a>
        <template #overlay>
          <a-menu>
            <a-menu-item>
              <a href="javascript:;">International</a>
            </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
    </p>
  </a-layout-header>
</template>

<style lang="less">
@import '@/assets/global.less';

.ant-layout-header {
  background-color: #fff !important;
  box-shadow: inset 0 -1px 0 0 rgba(61, 61, 61, .15);
  display: flex;
  padding-left: 2em;
  p:first-of-type {
    padding: 0 2em;
    font-size: 1.2em;
    margin-right: auto;
  }
}

</style>