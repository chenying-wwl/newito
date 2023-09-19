import { createPinia } from 'pinia'
// pinia持久化插件
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

const pinia = createPinia()

pinia.use(piniaPluginPersistedstate)

// pinia.use( ({store}) => {
//     const initState = JSON.parse(JSON.stringify(store.$state))
//     store.$reset = () => {
//         store.$state = initState
//     }
// })

export default pinia
