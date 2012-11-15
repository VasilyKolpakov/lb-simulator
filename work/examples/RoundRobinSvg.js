{
    seed : 0,
    clusterModel :
    { 
        type : "RoundRobin"
    },
    servers :
    {
        type : "RandomPerformance",
        numberOfServers : 3,
        maxPerf: 2,
        minPerf: 1
    },
    taskGenerator :
    { 
        type : "RandomTaskGen",
        tasks : 25,
        maxArrivalTime : 20000,
        minExecTime : 1000,
        maxExecTime : 5000
    },
    outputFormat : {type : "SVG"}
}
