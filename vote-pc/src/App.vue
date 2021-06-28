<template>
    <div id="app" class="app">
    <router-view/> 
 </div>
 
</template>

<script>
export default {
    name: 'App',
};
</script>

<style lang="scss">
@import './assets/style/common';
@import './assets/style/transition';
html,
body {
    height: 100%;
}
</style>
