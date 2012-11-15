{
    seed: 0,
    clusterModel :
    {
        type : "DynamicWRR",
        maxWeight : 3,
        refreshTime : 1000
    },
    servers :
    {
        type : "RandomPerformance",
        numberOfServers : 2,
        maxPerf: 2,
        minPerf: 1
    },
    taskGenerator :
    {
        type : "RandomTaskGen",
        tasks : 10,
        maxArrivalTime : 10000,
        minExecTime : 10000,
        maxExecTime : 20000
    },
    outputFormat : {type : "JSON"}
}
