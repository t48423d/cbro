<template>

<div>
    <!-- 增加一个添加按钮id为cc，触发事件:每次点击新增都多一个下拉框 -->
    <button id="cc">添加</button>
    
    <select>
        <option>1</option>
        <option>2</option>
        <option>3</option>
    </select>

    
</div>
</template>

<script>


 
import { defineComponent } from '@vue/composition-api'



export default defineComponent({
    setup() {
        
    },
})
</script>