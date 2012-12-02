{
    type : "ComparingRunner",
    seed : 1,
    metricPath : ["uniformityMetrics", "balancing efficiency"],
    clusterModels :
    {
        "Master-Slave" : {
            type : "MasterSlave"
        },
        "Random" :{
            type : "Random"
        },
        "Round Robin" :{
            type : "RoundRobin"
        },
        "Weighted RR" : {
            type : "DynamicWRR",
            maxWeight : 3,
            refreshTime: 100
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
        maxArrivalTime : 70000,
        tasks : 1000,
        minExecTime : 2000,
        maxExecTime : 4000
    }
}
