{
    simulation: {
        type: "SingleSimulation"
    },
    seed: 0,
    clusterModel: {
        type: "SimpleCluster",
        scheduler: {
            type: "DynamicWRRScheduler",
            maxWeight: 3
        },
        servers: {
            type: "RandomPerformance",
            numberOfServers: 2,
            maxPerf: 2,
            minPerf: 1
        },
        monitoring : {
            type : "PeriodicMonitoring",
            refreshTime : 1000
        }
    },
    taskGenerator: {
        type: "RandomTaskGen",
        tasks: 10,
        maxArrivalTime: 10000,
        minExecTime: 10000,
        maxExecTime: 20000
    },
    outputFormat: {
        type: "JSON"
    }
}