{
    seed: 0,
    clusterModel :
    {
        type : "MasterSlave",
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
