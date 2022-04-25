<template>
  <div id="module-histogram">
    <h3>本模块内各题目平均分如下图：</h3>
    <div id="container" ref="histogram"></div>
  </div>
</template>

<script>
export default {
  name: "histogram",
  mounted() {
    this.initHistogram();
  },
  methods: {
    initHistogram() {
      var echarts = require("echarts");
      var myChart = echarts.init(this.$refs.histogram);
      var option;
      option = {
        xAxis: {
          type: "category",
          data: [65, 70, 75, 80, 85, 90, 95, 105, 115, 120, 130, 145, 155, 165],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [
              120, 200, 150, 80, 70, 110, 130, 120, 200, 150, 80, 70, 110, 130,
            ],
            type: "bar",
            itemStyle: {
              normal: {
                //这里是重点
                color: function (params) {
                  //注意，如果颜色太少的话，后面颜色不会自动循环，最好多定义几个颜色
                  var colorList = [
                    "#c23531",
                    "#2f4554",
                    "#61a0a8",
                    "#d48265",
                    "#91c7ae",
                    "#749f83",
                    "#ca8622",
                  ];
                  return colorList[params.dataIndex % colorList.length];
                },
                label: {
                  show: true, //开启显示
                  position: "top", //在上方显示
                  textStyle: {
                    //数值样式
                    color: "black",
                    fontSize: 16,
                  },
                },
              },
            },
          },
        ],
      };
      option && myChart.setOption(option);
    },
  },
};
</script>

<style scoped>
#module-histogram {
  padding: 0 20px;
  box-sizing: border-box;
  width: 100%;
}
#container {
  height: 400px;
  width: 100%;
}
</style>