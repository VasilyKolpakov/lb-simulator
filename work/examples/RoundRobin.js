{
    clusterModel : 
    { 
        type : "RoundRobin"
    },
    servers :
    {
        type : "RandomPerformance",
        numberOfServers : 5,
        maxPerf: 2,
        minPerf: 1,
        seed: 0
    },
    taskGenerator :
    { 
        type : "RandomTaskGen",
        tasks : 20,
        maxArrivalTime : 100,
        minExecTime : 10,
        maxExecTime : 20,
        seed : 1 
    }
}
