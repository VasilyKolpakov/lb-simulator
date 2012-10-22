{
    type : "MultiRunner",
    seed : 1,
    maxArrivalTime : 2400,
    numberOfServers : 20,
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
            type : "RoundRobin"
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
            minExecTime : 20,
            maxExecTime : 40
        },
        // маленькие задания часто
        {
            type : "RandomTaskGen",
            tasks : 320,
            minExecTime : 20,
            maxExecTime : 40
        },
        // большие задания редко
        {
            type : "RandomTaskGen",
            tasks : 40,
            minExecTime : 40,
            maxExecTime : 80
        },
        // большие задания часто
        {
            type : "RandomTaskGen",
            tasks : 160,
            minExecTime : 40,
            maxExecTime : 80
        },

        // одинаковые задачи
        // маленькие задания редко
        {
            type : "RandomTaskGen",
            tasks : 80,
            minExecTime : 30,
            maxExecTime : 30
        },
        // маленькие задания часто
        {
            type : "RandomTaskGen",
            tasks : 320,
            minExecTime : 30,
            maxExecTime : 30
        },
        // большие задания редко
        {
            type : "RandomTaskGen",
            tasks : 40,
            minExecTime : 60,
            maxExecTime : 60
        },
        // большие задания часто
        {
            type : "RandomTaskGen",
            tasks : 160,
            minExecTime : 60,
            maxExecTime : 60
        }
    ]
}
