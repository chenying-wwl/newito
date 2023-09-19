import { reactive } from 'vue'
import { message } from 'ant-design-vue'
import http from '@/utils/http.js'
import { useRouter } from 'vue-router'

export default function useRegister() {

    // 校验手机号的正则
    const phoneReg = /^1\d{10}$/;

    const router = useRouter()

    // 表单数据
    const modelRef = reactive({
        name: 'tanent08',
        pwd: '123456',
        repeatPwd: '123456',  // 重复密码
        phone: '13072727272',
        code: '123456',
        companyName: '万锋科技',
        companyCode: '1098766',
        code: '123456'
    })

    // 提交
    const onSubmit = async () => {
        if(modelRef.name && modelRef.pwd && modelRef.repeatPwd 
            && modelRef.companyName && modelRef.companyCode && modelRef.phone && modelRef.code) {
            if(!phoneReg.test(modelRef.phone)) {
                message.error('请输入正确的手机号')
                return
            }
            const r = await http.post(`/auth/tenant/regist`, modelRef)
            if(r.code === 200) {
                router.push('/animate')
            }else {
                message.error(r.message)
            }
        }else {
            message.error('您还有参数没有输入')
        }
    }

    // 发送验证码
    const sendCode = () => {
        if(reg.test(modelRef.phone)) {

        }else {
            message.error('请输入正确的手机号')
        }
    }
    // 校验用户名是否可有
    const validateName = async () => {
        if(modelRef.name) {
            const r = await http.get(`/auth/tenant/isexist?name=${modelRef.name}`)
            if(r.code === 200) {
                message.success('恭喜你，该账户名可用')
            }else {
                message.warn('账号名已经被占用')
            }
        }
    }

    return { modelRef, onSubmit, sendCode, validateName}
}