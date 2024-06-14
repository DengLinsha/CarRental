// 重置 message，防止重复点击重复弹出 message 弹框
import { Message } from 'element-ui'
let messageInstance = null
const mainMessage = options => {
  // 如果弹窗已存在先关闭
  if (messageInstance) messageInstance.close()
      messageInstance = Message(options)
}
const arr = ['success', 'warning', 'info', 'error']
arr.forEach(type => {
  mainMessage[type] = options => {
    if (typeof options === 'string') {
      options = {
        message: options
      }
    }
    options.type = type
    return mainMessage(options)
  }
})
const message = mainMessage
export default message