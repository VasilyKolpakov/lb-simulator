{
    clusterModel : 
    { 
        type : "Random",
        servers : 5
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
