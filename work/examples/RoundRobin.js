{
    seed : 1,
    clusterModel :
    { 
        type : "RoundRobin",
        refreshTime : 5
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
        maxArrivalTime : 100,
        minExecTime : 10,
        maxExecTime : 20
    }
}
