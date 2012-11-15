{
    seed : 1,
    clusterModel :
    { 
        type : "RoundRobin",
        refreshTime : 0
    },
    servers :
    {
        type : "RandomPerformance",
        numberOfServers : 5,
        maxPerf: 2,
        minPerf: 1
    },
    taskGenerator :
    { 
        type : "RandomTaskGen",
        tasks : 20,
        maxArrivalTime : 5000,
        minExecTime : 1000,
        maxExecTime : 2000
    },
    outputFormat : {type : "JSON"}
}
