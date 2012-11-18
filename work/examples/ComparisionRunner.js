{
    type : "ComparingRunner",
    seed : 1,
    metricPath : ["performanceMetrics", "makespan"],
    clusterModels :
    {
        "Master-Slave" : {
            type : "MasterSlave"
        },
        "Random" :{
            type : "Random"
        }
    },
    servers :
    {
        type : "RandomPerformance",
        numberOfServers : 20,
        maxPerf: 3,
        minPerf: 3
    },
    taskGenerator :
    {
        type : "RandomTaskGen",
        maxArrivalTime : 8000,
        tasks : 320,
        minExecTime : 2000,
        maxExecTime : 4000
    }
}
