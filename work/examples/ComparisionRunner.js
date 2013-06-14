{
    simulation: {
        type: "ComparingSimulation"
    },
    seed: 1,
    metricPath: ["uniformityMetrics", "balancing efficiency"],
    clusterModels: {
        "Master-Slave": {
            type: "SimpleCluster",
            scheduler: {
                type: "MasterSlaveScheduler"
            }
        },
        "Random": {
            type: "SimpleCluster",
            scheduler: {
                type: "RandomScheduler"
            }
        },
        "Round Robin": {
            type: "SimpleCluster",
            scheduler: {
                type: "RoundRobinScheduler"
            }
        },
        "Weighted RR": {
            type: "SimpleCluster",
            scheduler: {
                type: "DynamicWRRScheduler",
                maxWeight: 3,
                refreshTime: 100,
                monitoring :
                {
                    type : "PeriodicMonitoring",
                    refreshTime : 100
                }
            }
        }
    },
    servers: {
        type: "RandomPerformance",
        numberOfServers: 20,
        maxPerf: 3,
        minPerf: 3
    },
    taskGenerator: {
        type: "RandomTaskGen",
        maxArrivalTime: 70000,
        tasks: 1000,
        minExecTime: 2000,
        maxExecTime: 4000
    }
}