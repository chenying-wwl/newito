<script setup>
    import useBreadcrumb from '@/hooks/useBreadcrumb'

    const { gotoDevice } = useBreadcrumb()

    // 让创建产品的按钮高亮
    const highlightCreateProuctBtn = () => {
        const divBox = document.createElement('div')
        // 绝对对位
        divBox.style.position = 'fixed'
        divBox.style.height = '100%'
        divBox.style.zIndex = 9999
        divBox.style.top = 0
        divBox.style.bottom = 0
        divBox.style.right = 0
        divBox.style.left = 0
        divBox.style.backgroundColor = 'rgba(0, 0, 0, 0.3)'

        const createProductBtn = document.querySelector('#createProductBtn')
        const t = createProductBtn.offsetTop
        const l = createProductBtn.offsetLeft

        const hlBox = document.createElement('div')
        hlBox.style.position = 'absolute'
        hlBox.style.left = (l - 5) + 'px'
        hlBox.style.top = (t - 5) + 'px'
        hlBox.style.width = '97px'
        hlBox.style.height = '42px'
        hlBox.style.backgroundColor = 'rgba(255, 255, 255, 0)'
        hlBox.style.border = '3px solid red'

        divBox.appendChild(hlBox)

        const spanBox = document.createElement('span')
        spanBox.style.position = 'absolute'
        spanBox.style.left = l + 100 + 'px'
        spanBox.style.top = t + 5 + 'px'
        spanBox.innerHTML = '我知道了'
        spanBox.style.color = 'red'
        spanBox.style.cursor = 'pointer'
        spanBox.addEventListener('click', () => {
            document.body.removeChild(divBox)
        })

        divBox.appendChild(spanBox)

        document.body.appendChild(divBox)        
    }
</script>
<template>
    <h2 style="margin-bottom: .5em;">设备接入流程概览</h2>
    <a-row class="tutorial" :gutter="4">  
        <a-col :span="4">
            <a-card @click="highlightCreateProuctBtn" hoverable>
                <div class="tutorial-item">
                    <img src="../../assets/images/product-one.png"/>
                    <div>
                        <p>创建产品</p>
                        <p>产品是同品类设备的集合</p>
                    </div>
                </div>
            </a-card>
        </a-col>
        <a-col :span="4">
            <a-card @click="gotoDevice" hoverable>
                <div class="tutorial-item">
                    <img src="../../assets/images/product-two.png"/>
                    <div>
                        <p>创建设备</p>
                        <p>创建设备获取连接平台所需的身份信息</p>
                    </div>
                </div>
            </a-card>
        </a-col>
        <a-col :span="4">
            <a-card hoverable>
                <div class="tutorial-item">
                    <img src="../../assets/images/product-three.png"/>
                    <div>
                        <p>设备端开发</p>
                        <p>集成Link SDK，开发设备端程序</p>
                    </div>
                </div>
            </a-card>
        </a-col>
        <a-col :span="4">
            <a-card hoverable>
                <div class="tutorial-item">
                    <img src="../../assets/images/product-four.png"/>
                    <div>
                        <p>查看上报数据</p>
                        <p>查看设备上报的属性数据 SDK，开发设备端程序</p>
                    </div>
                </div>
            </a-card>
        </a-col>
        <a-col :span="4">
            <a-card hoverable>
                <div class="tutorial-item">
                    <img style="width: 41px;" src="../../assets/images/product-next.png"/>
                    <div>
                        <p>接下来，建议您可以：</p>
                        <p>配置数据流转</p>
                    </div>
                </div>
            </a-card>
        </a-col>
    </a-row>
</template>
<style lang="less" scoped>
@import '@/assets/global.less';
:deep(.ant-card-body) {
    padding: 0.6em;
}

.tutorial {
    padding: 0.6em;
    border: 1px solid transparent;
    box-shadow: 0 1px 4px 0 rgba(0,0,0,.08);
    background-color: #fff;
    .tutorial-item {
        height: 70px;
        display: flex;
        > img {
            height: 32px;
            width: 24px;
            margin-right: 6px;
        }
        > div > p:last-of-type{
            font-size: @sm-size;
            color: #888;
        }
    }
}   
</style>

