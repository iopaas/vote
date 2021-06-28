<template>
     <div>
        <div id="myChart" ref="myChart" :style="{width: '800px', height: '300px'}"></div>
        
      </div>
</template>
<script>
import echarts from 'echarts';
  export default {
 
  data () {
          return {
                    
           }
   },
 mounted(){
    this.drawLine();
    
 },
 methods: {
  drawLine(){
    // 初始化echarts实例
    let myChart = this.$echarts.init(this.$refs.myChart)
    // 绘制图表
    myChart.setOption({
      title: { text: 'echarts表格' },
      tooltip: {},
      xAxis: {
        data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
      },
      yAxis: {},
      series: [
        {
            name:'echarts表格',//系列名称，用于tooltip的显示，legend 的图例筛选，在 setOption 更新数据和配置项时用于指定对应的系列。
            type:'bar',
            itemStyle:{//
              normal: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            {offset: 0, color: 'rgba(255,255,255,0)'},
                            {offset: 0.5, color: '#44C0C1'},
                            {offset: 1, color: '#188df0'}
                        ]
                    )
                },
            },
            label:{//label要加入normal才可生效,label即为x轴对应Y轴的值
                normal:{
                  show:true,  
                  color:'red',//设置渐变时候控制不到颜色，只能通过全局textStyle来控制
                  position:'top'
                }
            },
            barWidth: '40%',
            data:[10, 52, 200, 334, 390, 330]
        }
      ]
    });
  },
 }
}
</script>
 
<style>
  
</style>
