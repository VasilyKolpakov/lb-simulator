[ {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9994172692025328,
      "min-max slowdown" : 1.0016406976846235,
      "average utilization" : 0.43400637159993066,
      "makespan" : 9492.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.43633243940457295,
      "coefficient of variation" : 0.25714002851115264,
      "standard deviation" : 0.11160041076722807,
      "Jain index" : 0.9379798403571984,
      "balancing efficiency" : 0.68149033446874
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.5909839996506285,
      "min-max slowdown" : 12.52424245092906,
      "average utilization" : 0.6800722975475341,
      "makespan" : 24068.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5092099351001045,
      "coefficient of variation" : 0.19713638131100739,
      "standard deviation" : 0.13406699176838355,
      "Jain index" : 0.9625910614946079,
      "balancing efficiency" : 0.6840192716540613
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.999732472812636,
      "min-max slowdown" : 1.0006839522511033,
      "average utilization" : 0.3949516827455636,
      "makespan" : 10577.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.40589274495031424,
      "coefficient of variation" : 0.24970358774420234,
      "standard deviation" : 0.0986208521671772,
      "Jain index" : 0.9413076939905703,
      "balancing efficiency" : 0.7037866338429504
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6598413023224676,
      "min-max slowdown" : 7.016559937167942,
      "average utilization" : 0.6469029478897607,
      "makespan" : 25765.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.39468633758758537,
      "coefficient of variation" : 0.23072450486299295,
      "standard deviation" : 0.14925636234627557,
      "Jain index" : 0.9494568088409137,
      "balancing efficiency" : 0.6537272847797896
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9995605040172869,
      "min-max slowdown" : 1.0011179771876737,
      "average utilization" : 0.44982499762634304,
      "makespan" : 9235.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672797,
      "coefficient of variation" : 0.21152715898186164,
      "standard deviation" : 0.095150203786923,
      "Jain index" : 0.9571725224883144,
      "balancing efficiency" : 0.6965909078358221
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.4919123198840225,
      "min-max slowdown" : 10.907219954169411,
      "average utilization" : 0.692730871401889,
      "makespan" : 23987.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672797,
      "coefficient of variation" : 0.2115271589818616,
      "standard deviation" : 0.1465313931666709,
      "Jain index" : 0.9571725224883142,
      "balancing efficiency" : 0.6965909078358222
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9997799006107237,
      "min-max slowdown" : 1.0004623662006484,
      "average utilization" : 0.39371944394647695,
      "makespan" : 10551.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672798,
      "coefficient of variation" : 0.21152715898186158,
      "standard deviation" : 0.08328235541391657,
      "Jain index" : 0.9571725224883144,
      "balancing efficiency" : 0.6965909078358223
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.667441788059916,
      "min-max slowdown" : 5.585593258440779,
      "average utilization" : 0.6889110867461489,
      "makespan" : 24120.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672798,
      "coefficient of variation" : 0.2115271589818616,
      "standard deviation" : 0.1457234049705197,
      "Jain index" : 0.9571725224883139,
      "balancing efficiency" : 0.6965909078358221
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.999631535928015,
      "min-max slowdown" : 1.0009620009620008,
      "average utilization" : 0.4368601437258154,
      "makespan" : 9045.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.6566321058276725,
      "coefficient of variation" : 0.09734311947740142,
      "standard deviation" : 0.0425253291656168,
      "Jain index" : 0.9906132628353367,
      "balancing efficiency" : 0.8476367536646408
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.591176890328761,
      "min-max slowdown" : 11.609569516777638,
      "average utilization" : 0.8685843621399176,
      "makespan" : 18225.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.835621247113164,
      "coefficient of variation" : 0.04477764172027807,
      "standard deviation" : 0.03889315937173749,
      "Jain index" : 0.9979989749316662,
      "balancing efficiency" : 0.877410623556582
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9998479258372915,
      "min-max slowdown" : 1.0004198152812762,
      "average utilization" : 0.3890918462678393,
      "makespan" : 10347.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5864540765056608,
      "coefficient of variation" : 0.1500827287312034,
      "standard deviation" : 0.058395966014939224,
      "Jain index" : 0.9779713656798545,
      "balancing efficiency" : 0.809124405439807
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6792342613124287,
      "min-max slowdown" : 6.278953020371697,
      "average utilization" : 0.9089952651515152,
      "makespan" : 17600.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.763858568532668,
      "coefficient of variation" : 0.05786397891557505,
      "standard deviation" : 0.052598082857084824,
      "Jain index" : 0.9966629332446375,
      "balancing efficiency" : 0.9355011305161391
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0,
      "min-max slowdown" : 1.0,
      "average utilization" : 0.44848077138692677,
      "makespan" : 8919.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 0.9999999999999997,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.5209375000000005,
      "min-max slowdown" : 8.501,
      "average utilization" : 0.9735913350371181,
      "makespan" : 16434.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 0.9999999999999998,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0,
      "min-max slowdown" : 1.0,
      "average utilization" : 0.4032664583123299,
      "makespan" : 9919.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 0.9999999999999994,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6812781249999995,
      "min-max slowdown" : 4.528,
      "average utilization" : 0.9425625920471281,
      "makespan" : 16975.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 1.0000000000000004,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0728918913095484,
      "min-max slowdown" : 4.666729973020771,
      "average utilization" : 0.43755859078421305,
      "makespan" : 9377.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.4387182408420083,
      "coefficient of variation" : 0.22661081664493593,
      "standard deviation" : 0.09915550958761786,
      "Jain index" : 0.9511558073378863,
      "balancing efficiency" : 0.708134604003002
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.582776008657234,
      "min-max slowdown" : 13.371023775234343,
      "average utilization" : 0.8459628479441881,
      "makespan" : 18832.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.7538948850923031,
      "coefficient of variation" : 0.08715747084615841,
      "standard deviation" : 0.0737319822566287,
      "Jain index" : 0.9924608458923645,
      "balancing efficiency" : 0.8612233399931705
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0950574559981308,
      "min-max slowdown" : 2.920450881283652,
      "average utilization" : 0.29220479771088403,
      "makespan" : 14346.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.15333601037679928,
      "coefficient of variation" : 0.41709133586695385,
      "standard deviation" : 0.12187608942396563,
      "Jain index" : 0.8518140188012714,
      "balancing efficiency" : 0.4016688529399069
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6620455887898355,
      "min-max slowdown" : 6.848561569190702,
      "average utilization" : 0.6378062002875782,
      "makespan" : 25772.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.49968166153045335,
      "coefficient of variation" : 0.19728965154893352,
      "standard deviation" : 0.1258325630104856,
      "Jain index" : 0.9625350494043066,
      "balancing efficiency" : 0.6450365782799019
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.066379091302519,
      "min-max slowdown" : 3.6871152630719775,
      "average utilization" : 0.452667958273867,
      "makespan" : 9177.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672798,
      "coefficient of variation" : 0.21152715898186158,
      "standard deviation" : 0.09575156717579096,
      "Jain index" : 0.9571725224883141,
      "balancing efficiency" : 0.6965909078358222
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.500500130355917,
      "min-max slowdown" : 9.700296991299089,
      "average utilization" : 0.7707983300946872,
      "makespan" : 21047.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.6587442452157971,
      "coefficient of variation" : 0.10900435462492394,
      "standard deviation" : 0.08402037451794048,
      "Jain index" : 0.9882575735884045,
      "balancing efficiency" : 0.7772491169160489
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.130141579653309,
      "min-max slowdown" : 3.6558129610849903,
      "average utilization" : 0.2892728150072218,
      "makespan" : 14446.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.206748111928364,
      "coefficient of variation" : 0.4147233934254033,
      "standard deviation" : 0.11996820346551396,
      "Jain index" : 0.8532456019894303,
      "balancing efficiency" : 0.39690669071410956
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.692971254150376,
      "min-max slowdown" : 5.9202078586084275,
      "average utilization" : 0.7232411932037419,
      "makespan" : 22547.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5659002901473718,
      "coefficient of variation" : 0.1727566835337327,
      "standard deviation" : 0.12494474993285806,
      "Jain index" : 0.971020031729491,
      "balancing efficiency" : 0.7294114190202303
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0701652456138429,
      "min-max slowdown" : 4.530879720534893,
      "average utilization" : 0.43213035870516175,
      "makespan" : 9144.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.7157496911112726,
      "coefficient of variation" : 0.08761518240063342,
      "standard deviation" : 0.03786118019880389,
      "Jain index" : 0.9923820583340914,
      "balancing efficiency" : 0.8615597063740095
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.648777355610433,
      "min-max slowdown" : 13.122190074341972,
      "average utilization" : 0.8949542062415198,
      "makespan" : 17688.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.7741935483870968,
      "coefficient of variation" : 0.06890837514788509,
      "standard deviation" : 0.06166984018386837,
      "Jain index" : 0.9952740762415024,
      "balancing efficiency" : 0.9064678373735446
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0893044540124817,
      "min-max slowdown" : 2.8318107340204106,
      "average utilization" : 0.33985592886487714,
      "makespan" : 11846.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.18541403951240015,
      "coefficient of variation" : 0.29962152770726164,
      "standard deviation" : 0.10182815260686492,
      "Jain index" : 0.9176222434084564,
      "balancing efficiency" : 0.5076839007986551
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6783593156643475,
      "min-max slowdown" : 6.556005233318796,
      "average utilization" : 0.836600777423347,
      "makespan" : 19123.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.7214930049154648,
      "coefficient of variation" : 0.07324822923297925,
      "standard deviation" : 0.06127952552119397,
      "Jain index" : 0.9946633297674987,
      "balancing efficiency" : 0.8641666216532526
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0642375,
      "min-max slowdown" : 3.583,
      "average utilization" : 0.4435081494622465,
      "makespan" : 9019.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 2.5032753647735713E-16,
      "standard deviation" : 1.1102230246251565E-16,
      "Jain index" : 1.0000000000000002,
      "balancing efficiency" : 1.0000000000000002
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.549325000000001,
      "min-max slowdown" : 9.476,
      "average utilization" : 0.912512832211703,
      "makespan" : 17534.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.8823529411764706,
      "coefficient of variation" : 0.03952847075210477,
      "standard deviation" : 0.036070236799000586,
      "Jain index" : 0.9984399375975042,
      "balancing efficiency" : 0.9411764705882353
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.1241500000000002,
      "min-max slowdown" : 3.545,
      "average utilization" : 0.3348401138456386,
      "makespan" : 11946.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.25,
      "coefficient of variation" : 0.27386127875258315,
      "standard deviation" : 0.09169974175542711,
      "Jain index" : 0.9302325581395342,
      "balancing efficiency" : 0.49999999999999983
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "DynamicWRR",
      "maxWeight" : 5,
      "refreshTime" : 100
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6824218749999993,
      "min-max slowdown" : 5.4805,
      "average utilization" : 0.9370424597364568,
      "makespan" : 17075.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 0.9999999999999997,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.40334882148693,
      "min-max slowdown" : 3.602690258198085,
      "average utilization" : 0.34758798724463597,
      "makespan" : 11985.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.1156749354184805,
      "coefficient of variation" : 0.5529465229219606,
      "standard deviation" : 0.19219756895636422,
      "Jain index" : 0.7658434687755321,
      "balancing efficiency" : 0.41853691852905706
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 5.3803256743159125,
      "min-max slowdown" : 19.1941476462807,
      "average utilization" : 0.6898975335148941,
      "makespan" : 23564.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.33270076995993114,
      "coefficient of variation" : 0.2758815747121547,
      "standard deviation" : 0.1903300179361205,
      "Jain index" : 0.9292724742181842,
      "balancing efficiency" : 0.697422062583878
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.2955540077792764,
      "min-max slowdown" : 3.461483443998995,
      "average utilization" : 0.30989736052835404,
      "makespan" : 15425.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.09595275319031424,
      "coefficient of variation" : 0.621052336711027,
      "standard deviation" : 0.19246247989671386,
      "Jain index" : 0.7216537970097527,
      "balancing efficiency" : 0.32062199835617794
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 3.5582903242428885,
      "min-max slowdown" : 14.583467472607596,
      "average utilization" : 0.48351486329899374,
      "makespan" : 35024.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.1823352225005489,
      "coefficient of variation" : 0.44599190554055634,
      "standard deviation" : 0.21564371523989984,
      "Jain index" : 0.8340918148588411,
      "balancing efficiency" : 0.4866165506867618
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.4168829701028052,
      "min-max slowdown" : 3.638606085436984,
      "average utilization" : 0.3913528152544322,
      "makespan" : 10765.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.11306009444256081,
      "coefficient of variation" : 0.5338692977959135,
      "standard deviation" : 0.2089312526703376,
      "Jain index" : 0.7782001684088453,
      "balancing efficiency" : 0.4477592681407487
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 5.249876513497027,
      "min-max slowdown" : 15.764111181895478,
      "average utilization" : 0.6772791573679895,
      "makespan" : 24323.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.31960038008771363,
      "coefficient of variation" : 0.27715298774575375,
      "standard deviation" : 0.18770994200246482,
      "Jain index" : 0.9286656800444211,
      "balancing efficiency" : 0.6808829718605085
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.2980336738560616,
      "min-max slowdown" : 3.248632928324939,
      "average utilization" : 0.28676734864534564,
      "makespan" : 16648.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.09452113129546763,
      "coefficient of variation" : 0.6593174373420504,
      "standard deviation" : 0.1890707134222236,
      "Jain index" : 0.6970100789199529,
      "balancing efficiency" : 0.29598564568224117
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 3.4402475628622255,
      "min-max slowdown" : 10.230458307563781,
      "average utilization" : 0.4804968291388415,
      "makespan" : 35176.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.2185756797215375,
      "coefficient of variation" : 0.44333105813186574,
      "standard deviation" : 0.2130191676911289,
      "Jain index" : 0.8357413639063681,
      "balancing efficiency" : 0.48364172140297934
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.3784888527530135,
      "min-max slowdown" : 3.5145885504629892,
      "average utilization" : 0.38652059082461115,
      "makespan" : 10223.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.14100838146523648,
      "coefficient of variation" : 0.45128883338982134,
      "standard deviation" : 0.17443242651438326,
      "Jain index" : 0.8307982831245576,
      "balancing efficiency" : 0.5147956746428106
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 5.441932184990952,
      "min-max slowdown" : 21.8198439826118,
      "average utilization" : 0.6054212720388572,
      "makespan" : 26147.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.38350646867631133,
      "coefficient of variation" : 0.25195537285309677,
      "standard deviation" : 0.1525391423297464,
      "Jain index" : 0.9403078386243479,
      "balancing efficiency" : 0.6053132368873877
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.214371000492167,
      "min-max slowdown" : 2.287352594385623,
      "average utilization" : 0.35688999994090154,
      "makespan" : 12534.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.16919100853467964,
      "coefficient of variation" : 0.46562454311490487,
      "standard deviation" : 0.1661767431647607,
      "Jain index" : 0.821823547207913,
      "balancing efficiency" : 0.40328698695088894
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 3.49893434091621,
      "min-max slowdown" : 13.212396748312678,
      "average utilization" : 0.5566568081651588,
      "makespan" : 28740.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.29866254404256853,
      "coefficient of variation" : 0.35092096809650564,
      "standard deviation" : 0.19534254601882836,
      "Jain index" : 0.8903565717749575,
      "balancing efficiency" : 0.575189353083579
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.3829249999999997,
      "min-max slowdown" : 3.804,
      "average utilization" : 0.4260304611779741,
      "makespan" : 9389.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.14285714285714288,
      "coefficient of variation" : 0.4330127018922194,
      "standard deviation" : 0.18447660108306285,
      "Jain index" : 0.8421052631578945,
      "balancing efficiency" : 0.5714285714285713
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 5.304759374999999,
      "min-max slowdown" : 18.11,
      "average utilization" : 0.6153136176595009,
      "makespan" : 26003.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.3846153846153846,
      "coefficient of variation" : 0.24286699034656803,
      "standard deviation" : 0.14943936644022185,
      "Jain index" : 0.9443009959424566,
      "balancing efficiency" : 0.6153846153846154
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.2238499999999999,
      "min-max slowdown" : 2.3005,
      "average utilization" : 0.3549875754348599,
      "makespan" : 12520.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.16666666666666666,
      "coefficient of variation" : 0.48733971724044794,
      "standard deviation" : 0.17299954463629683,
      "Jain index" : 0.8080808080808088,
      "balancing efficiency" : 0.3703703703703705
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "Random",
      "seed" : 1
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 3.4042656249999985,
      "min-max slowdown" : 9.8475,
      "average utilization" : 0.5941329372447085,
      "makespan" : 26930.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.38461538461538464,
      "coefficient of variation" : 0.3377314021526574,
      "standard deviation" : 0.2006573499607322,
      "Jain index" : 0.8976157082748945,
      "balancing efficiency" : 0.6153846153846154
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9994172692025328,
      "min-max slowdown" : 1.0016406976846235,
      "average utilization" : 0.43400637159993066,
      "makespan" : 9492.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.43633243940457295,
      "coefficient of variation" : 0.25714002851115264,
      "standard deviation" : 0.11160041076722807,
      "Jain index" : 0.9379798403571984,
      "balancing efficiency" : 0.68149033446874
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.5909839996506285,
      "min-max slowdown" : 12.52424245092906,
      "average utilization" : 0.6800722975475341,
      "makespan" : 24068.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5092099351001045,
      "coefficient of variation" : 0.19713638131100739,
      "standard deviation" : 0.13406699176838355,
      "Jain index" : 0.9625910614946079,
      "balancing efficiency" : 0.6840192716540613
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.999732472812636,
      "min-max slowdown" : 1.0006839522511033,
      "average utilization" : 0.3949516827455636,
      "makespan" : 10577.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.40589274495031424,
      "coefficient of variation" : 0.24970358774420234,
      "standard deviation" : 0.0986208521671772,
      "Jain index" : 0.9413076939905703,
      "balancing efficiency" : 0.7037866338429504
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6598413023224676,
      "min-max slowdown" : 7.016559937167942,
      "average utilization" : 0.6469029478897607,
      "makespan" : 25765.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.39468633758758537,
      "coefficient of variation" : 0.23072450486299295,
      "standard deviation" : 0.14925636234627557,
      "Jain index" : 0.9494568088409137,
      "balancing efficiency" : 0.6537272847797896
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9995605040172869,
      "min-max slowdown" : 1.0011179771876737,
      "average utilization" : 0.44982499762634304,
      "makespan" : 9235.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672797,
      "coefficient of variation" : 0.21152715898186164,
      "standard deviation" : 0.095150203786923,
      "Jain index" : 0.9571725224883144,
      "balancing efficiency" : 0.6965909078358221
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.4919123198840225,
      "min-max slowdown" : 10.907219954169411,
      "average utilization" : 0.692730871401889,
      "makespan" : 23987.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672797,
      "coefficient of variation" : 0.2115271589818616,
      "standard deviation" : 0.1465313931666709,
      "Jain index" : 0.9571725224883142,
      "balancing efficiency" : 0.6965909078358222
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9997799006107237,
      "min-max slowdown" : 1.0004623662006484,
      "average utilization" : 0.39371944394647695,
      "makespan" : 10551.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672798,
      "coefficient of variation" : 0.21152715898186158,
      "standard deviation" : 0.08328235541391657,
      "Jain index" : 0.9571725224883144,
      "balancing efficiency" : 0.6965909078358223
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 4,
      "minPerf" : 2,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.667441788059916,
      "min-max slowdown" : 5.585593258440779,
      "average utilization" : 0.6889110867461489,
      "makespan" : 24120.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672798,
      "coefficient of variation" : 0.2115271589818616,
      "standard deviation" : 0.1457234049705197,
      "Jain index" : 0.9571725224883139,
      "balancing efficiency" : 0.6965909078358221
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.999631535928015,
      "min-max slowdown" : 1.0009620009620008,
      "average utilization" : 0.4368601437258154,
      "makespan" : 9045.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.6566321058276725,
      "coefficient of variation" : 0.09734311947740142,
      "standard deviation" : 0.0425253291656168,
      "Jain index" : 0.9906132628353367,
      "balancing efficiency" : 0.8476367536646408
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 2000,
      "maxExecTime" : 4000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.591176890328761,
      "min-max slowdown" : 11.609569516777638,
      "average utilization" : 0.8685843621399176,
      "makespan" : 18225.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.835621247113164,
      "coefficient of variation" : 0.04477764172027807,
      "standard deviation" : 0.03889315937173749,
      "Jain index" : 0.9979989749316662,
      "balancing efficiency" : 0.877410623556582
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 0.9998479258372915,
      "min-max slowdown" : 1.0004198152812762,
      "average utilization" : 0.3890918462678393,
      "makespan" : 10347.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5864540765056608,
      "coefficient of variation" : 0.1500827287312034,
      "standard deviation" : 0.058395966014939224,
      "Jain index" : 0.9779713656798545,
      "balancing efficiency" : 0.809124405439807
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 4000,
      "maxExecTime" : 8000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6792342613124287,
      "min-max slowdown" : 6.278953020371697,
      "average utilization" : 0.9089952651515152,
      "makespan" : 17600.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.763858568532668,
      "coefficient of variation" : 0.05786397891557505,
      "standard deviation" : 0.052598082857084824,
      "Jain index" : 0.9966629332446375,
      "balancing efficiency" : 0.9355011305161391
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 80,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0,
      "min-max slowdown" : 1.0,
      "average utilization" : 0.44848077138692677,
      "makespan" : 8919.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 0.9999999999999997,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 320,
      "maxArrivalTime" : 8000,
      "minExecTime" : 3000,
      "maxExecTime" : 3000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 4.5209375000000005,
      "min-max slowdown" : 8.501,
      "average utilization" : 0.9735913350371181,
      "makespan" : 16434.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 0.9999999999999998,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 40,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 1.0,
      "min-max slowdown" : 1.0,
      "average utilization" : 0.4032664583123299,
      "makespan" : 9919.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 0.9999999999999994,
      "balancing efficiency" : 1.0
    }
  }
}, {
  "config" : {
    "clusterModel" : {
      "type" : "RoundRobin"
    },
    "servers" : {
      "type" : "RandomPerformance",
      "numberOfServers" : 20,
      "maxPerf" : 3,
      "minPerf" : 3,
      "seed" : 1
    },
    "taskGenerator" : {
      "type" : "RandomTaskGen",
      "tasks" : 160,
      "maxArrivalTime" : 8000,
      "minExecTime" : 6000,
      "maxExecTime" : 6000,
      "seed" : 1
    }
  },
  "result" : {
    "performanceMetrics" : {
      "average slowdown" : 2.6812781249999995,
      "min-max slowdown" : 4.528,
      "average utilization" : 0.9425625920471281,
      "makespan" : 16975.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 1.0,
      "coefficient of variation" : 0.0,
      "standard deviation" : 0.0,
      "Jain index" : 1.0000000000000004,
      "balancing efficiency" : 1.0
    }
  }
} ]