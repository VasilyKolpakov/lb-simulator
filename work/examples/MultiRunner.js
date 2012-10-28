{
    type : "MultiRunner",
    seed : 1,
    maxArrivalTime : 8000,
    numberOfServers : 20,
    refreshTime : 0,
    outputFormat : "csv",
    clusterModels :
    [
        {
            type : "RoundRobin"
        },
        {
            type : "DynamicWRR",
            maxWeight : 3
        },
        {
            type : "Random"
        },
        {
            type : "MasterSlave"
        }
    ],
    servers :
    [
        // количество серверов указано в начале файла
        // Все одинаковой производительности
        {
            type : "RandomPerformance",
            maxPerf: 4,
            minPerf: 2
        },
        // случайное распределение с max/min=2
        {
            type : "RandomPerformance",
            maxPerf: 3,
            minPerf: 3
        }
    ],
    // seed и maxArrivalTime указаны в начале файла
    taskGenerators :
    [
        // случайное распределение с max/min=2
        // маленькие задания редко
        {
            type : "RandomTaskGen",
            tasks : 80,
            minExecTime : 2000,
            maxExecTime : 4000
        },
        // маленькие задания часто
        {
            type : "RandomTaskGen",
            tasks : 320,
            minExecTime : 2000,
            maxExecTime : 4000
        },
        // большие задания редко
        {
            type : "RandomTaskGen",
            tasks : 40,
            minExecTime : 4000,
            maxExecTime : 8000
        },
        // большие задания часто
        {
            type : "RandomTaskGen",
            tasks : 160,
            minExecTime : 4000,
            maxExecTime : 8000
        },

        // одинаковые задачи
        // маленькие задания редко
        {
            type : "RandomTaskGen",
            tasks : 80,
            minExecTime : 3000,
            maxExecTime : 3000
        },
        // маленькие задания часто
        {
            type : "RandomTaskGen",
            tasks : 320,
            minExecTime : 3000,
            maxExecTime : 3000
        },
        // большие задания редко
        {
            type : "RandomTaskGen",
            tasks : 40,
            minExecTime : 6000,
            maxExecTime : 6000
        },
        // большие задания часто
        {
            type : "RandomTaskGen",
            tasks : 160,
            minExecTime : 6000,
            maxExecTime : 6000
        }
    ]
}
