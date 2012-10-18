{
    type : "MultiRunner",
    servers : 5,
    clusterModels : {
        clusterModel : 
        { 
            type : "RoundRobin"
        },
        clusterModel_wrr : 
        { 
            type : "DynamicWRR",
            maxWeight : 3
        },
        clusterModel_rand : 
        { 
            type : "Random"
        },
        clusterModel2 : 
        { 
            type : "RoundRobin",
            servers : 10
        }
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
