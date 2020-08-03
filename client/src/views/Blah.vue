<template>
  <div class="about">
    <Board boardId="1"/>
  </div>
</template>

<script>
  import Board from '@/components/Board.vue'

  export default {
    name: 'About',
    components: {
      Board
    },
  }
</script>
