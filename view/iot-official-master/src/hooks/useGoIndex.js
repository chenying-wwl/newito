import { useRouter } from 'vue-router'

export default function useGoIndex() {

    const router = useRouter()

    const goIndex = () => {
        router.push('/')
    }

    return {
        goIndex
    }
}