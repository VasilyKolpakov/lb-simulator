{
    clusterModel : 
    { 
        type : "DynamicWRR",
        servers : 5,
        maxWeight : 3
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
