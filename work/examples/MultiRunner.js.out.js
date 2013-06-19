[ {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
      "average slowdown" : 0.999417269202533,
      "min-max slowdown" : 1.0016406976846235,
      "average utilization" : 0.4340063715999308,
      "makespan" : 9492.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.43633243940457295,
      "coefficient of variation" : 0.2571400285111526,
      "standard deviation" : 0.11160041076722808,
      "Jain index" : 0.9379798403571986,
      "balancing efficiency" : 0.6814903344687402
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
      "average utilization" : 0.6800722975475342,
      "makespan" : 24068.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5092099351001045,
      "coefficient of variation" : 0.19713638131100739,
      "standard deviation" : 0.13406699176838358,
      "Jain index" : 0.9625910614946079,
      "balancing efficiency" : 0.6840192716540614
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
      "coefficient of variation" : 0.2497035877442024,
      "standard deviation" : 0.09862085216717723,
      "Jain index" : 0.9413076939905707,
      "balancing efficiency" : 0.7037866338429504
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
      "average slowdown" : 2.6598413023224663,
      "min-max slowdown" : 7.016559937167942,
      "average utilization" : 0.6469029478897607,
      "makespan" : 25765.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.39468633758758537,
      "coefficient of variation" : 0.23072450486299295,
      "standard deviation" : 0.14925636234627557,
      "Jain index" : 0.9494568088409139,
      "balancing efficiency" : 0.6537272847797896
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
      "average utilization" : 0.449824997626343,
      "makespan" : 9235.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672797,
      "coefficient of variation" : 0.21152715898186167,
      "standard deviation" : 0.095150203786923,
      "Jain index" : 0.9571725224883136,
      "balancing efficiency" : 0.6965909078358221
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
      "average slowdown" : 4.491912319884024,
      "min-max slowdown" : 10.907219954169411,
      "average utilization" : 0.692730871401889,
      "makespan" : 23987.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672797,
      "coefficient of variation" : 0.2115271589818616,
      "standard deviation" : 0.1465313931666709,
      "Jain index" : 0.9571725224883141,
      "balancing efficiency" : 0.6965909078358222
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
      "average slowdown" : 0.9997799006107243,
      "min-max slowdown" : 1.0004623662006484,
      "average utilization" : 0.3937194439464769,
      "makespan" : 10551.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672798,
      "coefficient of variation" : 0.21152715898186158,
      "standard deviation" : 0.08328235541391656,
      "Jain index" : 0.9571725224883139,
      "balancing efficiency" : 0.6965909078358222
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
      "average slowdown" : 2.6674417880599157,
      "min-max slowdown" : 5.585593258440779,
      "average utilization" : 0.688911086746149,
      "makespan" : 24120.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672798,
      "coefficient of variation" : 0.2115271589818616,
      "standard deviation" : 0.14572340497051972,
      "Jain index" : 0.9571725224883141,
      "balancing efficiency" : 0.6965909078358222
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
      "coefficient of variation" : 0.09734311947740139,
      "standard deviation" : 0.04252532916561679,
      "Jain index" : 0.9906132628353366,
      "balancing efficiency" : 0.8476367536646408
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
      "average utilization" : 0.8685843621399177,
      "makespan" : 18225.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.835621247113164,
      "coefficient of variation" : 0.044777641720278066,
      "standard deviation" : 0.03889315937173749,
      "Jain index" : 0.9979989749316669,
      "balancing efficiency" : 0.8774106235565821
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
      "average slowdown" : 0.9998479258372918,
      "min-max slowdown" : 1.0004198152812762,
      "average utilization" : 0.38909184626783927,
      "makespan" : 10347.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5864540765056608,
      "coefficient of variation" : 0.15008272873120343,
      "standard deviation" : 0.05839596601493923,
      "Jain index" : 0.9779713656798542,
      "balancing efficiency" : 0.8091244054398069
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
      "average slowdown" : 2.6792342613124296,
      "min-max slowdown" : 6.278953020371697,
      "average utilization" : 0.908995265151515,
      "makespan" : 17600.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.763858568532668,
      "coefficient of variation" : 0.057863978915575064,
      "standard deviation" : 0.052598082857084824,
      "Jain index" : 0.9966629332446372,
      "balancing efficiency" : 0.9355011305161389
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
    "scheduler" : {
      "type" : "RoundRobinScheduler"
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
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 1.0486621563227545,
      "min-max slowdown" : 1.6308983014436669,
      "average utilization" : 0.3867371072952994,
      "makespan" : 10466.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.3575263473594392,
      "coefficient of variation" : 0.2574445352021926,
      "standard deviation" : 0.09956335483307885,
      "Jain index" : 0.9378419998772196,
      "balancing efficiency" : 0.6016621724422834
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 4.6075955225608904,
      "min-max slowdown" : 12.064615850453078,
      "average utilization" : 0.7921645746501347,
      "makespan" : 20298.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.6608280275252927,
      "coefficient of variation" : 0.12674333697821522,
      "standard deviation" : 0.10040158162708654,
      "Jain index" : 0.9841900948475425,
      "balancing efficiency" : 0.7976981282954969
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 1.032825552411164,
      "min-max slowdown" : 1.7677024898340155,
      "average utilization" : 0.3480156450152871,
      "makespan" : 11672.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.16003881699645764,
      "coefficient of variation" : 0.5152063377923897,
      "standard deviation" : 0.17929986596278236,
      "Jain index" : 0.7902404854344075,
      "balancing efficiency" : 0.4656874378092508
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 2.6720010650924015,
      "min-max slowdown" : 6.900460562040995,
      "average utilization" : 0.6629770965636812,
      "makespan" : 24585.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.46975962910274044,
      "coefficient of variation" : 0.198334381512525,
      "standard deviation" : 0.13149115240392728,
      "Jain index" : 0.962152271307481,
      "balancing efficiency" : 0.6702907219754178
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 1.0902301369442824,
      "min-max slowdown" : 2.0154797539303395,
      "average utilization" : 0.39220152386313306,
      "makespan" : 10537.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5113018222672797,
      "coefficient of variation" : 0.20241959197816808,
      "standard deviation" : 0.07938927243359115,
      "Jain index" : 0.9606390713210814,
      "balancing efficiency" : 0.6929845820560767
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 4.496140998650155,
      "min-max slowdown" : 9.313071336436735,
      "average utilization" : 0.8199254828228494,
      "makespan" : 19837.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.7054209630553481,
      "coefficient of variation" : 0.11523606541701166,
      "standard deviation" : 0.09448498657564874,
      "Jain index" : 0.9868946793767517,
      "balancing efficiency" : 0.8361923695949897
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 0.9998963091549726,
      "min-max slowdown" : 1.006101042982778,
      "average utilization" : 0.4062508130456207,
      "makespan" : 9713.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.22737275098424037,
      "coefficient of variation" : 0.43328166568702203,
      "standard deviation" : 0.17602102896311353,
      "Jain index" : 0.8419400643639868,
      "balancing efficiency" : 0.5295043420001416
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 2.6608320037894075,
      "min-max slowdown" : 5.249410428725556,
      "average utilization" : 0.7723397789030518,
      "makespan" : 21139.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.6494351350012214,
      "coefficient of variation" : 0.1404366388707433,
      "standard deviation" : 0.10846480261531762,
      "Jain index" : 0.9806590023135607,
      "balancing efficiency" : 0.7822077478527537
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 1.0314266909207004,
      "min-max slowdown" : 1.3545878852882864,
      "average utilization" : 0.4368601437258154,
      "makespan" : 9045.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.4755366949575636,
      "coefficient of variation" : 0.2599095106872021,
      "standard deviation" : 0.11354410619451745,
      "Jain index" : 0.9367216834456393,
      "balancing efficiency" : 0.7397778332501248
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 4.6098467559709535,
      "min-max slowdown" : 12.271873283772017,
      "average utilization" : 0.8891232307346663,
      "makespan" : 17804.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.8097603190728825,
      "coefficient of variation" : 0.05692359986244662,
      "standard deviation" : 0.05061209501474594,
      "Jain index" : 0.9967701693866318,
      "balancing efficiency" : 0.893438875719607
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 0.9998479258372914,
      "min-max slowdown" : 1.0004198152812762,
      "average utilization" : 0.3890918462678393,
      "makespan" : 10347.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.20226451457480127,
      "coefficient of variation" : 0.47692073471140783,
      "standard deviation" : 0.18556596919227608,
      "Jain index" : 0.8146948881562377,
      "balancing efficiency" : 0.5819224283305228
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 2.6997365760516634,
      "min-max slowdown" : 6.920636455186304,
      "average utilization" : 0.8053519590569678,
      "makespan" : 19865.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.7076606093359453,
      "coefficient of variation" : 0.10294644532359151,
      "standard deviation" : 0.08290812141930544,
      "Jain index" : 0.9895131685269254,
      "balancing efficiency" : 0.8105612038100384
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 1.0730625000000003,
      "min-max slowdown" : 1.705,
      "average utilization" : 0.41562759767248547,
      "makespan" : 9624.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.6,
      "coefficient of variation" : 0.07905694150420949,
      "standard deviation" : 0.0328582466767288,
      "Jain index" : 0.9937888198757762,
      "balancing efficiency" : 0.8
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 4.560021874999998,
      "min-max slowdown" : 10.111,
      "average utilization" : 0.8887407654279841,
      "makespan" : 18003.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.7777777777777778,
      "coefficient of variation" : 0.06250000000000001,
      "standard deviation" : 0.05554629783924902,
      "Jain index" : 0.9961089494163422,
      "balancing efficiency" : 0.8888888888888887
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 1.0129625,
      "min-max slowdown" : 1.309,
      "average utilization" : 0.3796146910885452,
      "makespan" : 10537.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.33333333333333337,
      "coefficient of variation" : 0.49999999999999994,
      "standard deviation" : 0.18980734554427256,
      "Jain index" : 0.8000000000000004,
      "balancing efficiency" : 0.6666666666666669
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "DynamicWRRScheduler",
      "maxWeight" : 5,
      "monitoring" : {
        "type" : "PeriodicMonitoring",
        "refreshTime" : 100
      }
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
      "average slowdown" : 2.7358468749999996,
      "min-max slowdown" : 8.396,
      "average utilization" : 0.6649488820546919,
      "makespan" : 24062.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.5833333333333333,
      "coefficient of variation" : 0.12500000000000003,
      "standard deviation" : 0.0831186102568365,
      "Jain index" : 0.9846153846153841,
      "balancing efficiency" : 0.6666666666666665
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average slowdown" : 1.4033488214869299,
      "min-max slowdown" : 3.602690258198085,
      "average utilization" : 0.347587987244636,
      "makespan" : 11985.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.1156749354184805,
      "coefficient of variation" : 0.5529465229219602,
      "standard deviation" : 0.19219756895636417,
      "Jain index" : 0.7658434687755322,
      "balancing efficiency" : 0.41853691852905717
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average utilization" : 0.6898975335148942,
      "makespan" : 23564.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.33270076995993114,
      "coefficient of variation" : 0.27588157471215463,
      "standard deviation" : 0.1903300179361205,
      "Jain index" : 0.9292724742181845,
      "balancing efficiency" : 0.6974220625838781
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average utilization" : 0.27890762447551865,
      "makespan" : 15425.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.0,
      "coefficient of variation" : 0.7346246244278826,
      "standard deviation" : 0.2048924088804008,
      "Jain index" : 0.6494884173087775,
      "balancing efficiency" : 0.2885597985205602
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average slowdown" : 3.558290324242889,
      "min-max slowdown" : 14.583467472607596,
      "average utilization" : 0.4835148632989936,
      "makespan" : 35024.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.1823352225005489,
      "coefficient of variation" : 0.4459919055405565,
      "standard deviation" : 0.21564371523989986,
      "Jain index" : 0.834091814858841,
      "balancing efficiency" : 0.4866165506867617
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average slowdown" : 1.4168829701028054,
      "min-max slowdown" : 3.638606085436984,
      "average utilization" : 0.3913528152544322,
      "makespan" : 10765.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.11306009444256081,
      "coefficient of variation" : 0.5338692977959135,
      "standard deviation" : 0.2089312526703376,
      "Jain index" : 0.7782001684088452,
      "balancing efficiency" : 0.4477592681407487
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average slowdown" : 5.249876513497029,
      "min-max slowdown" : 15.764111181895478,
      "average utilization" : 0.6772791573679894,
      "makespan" : 24323.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.31960038008771363,
      "coefficient of variation" : 0.2771529877457538,
      "standard deviation" : 0.18770994200246482,
      "Jain index" : 0.928665680044421,
      "balancing efficiency" : 0.6808829718605084
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average slowdown" : 1.298033673856062,
      "min-max slowdown" : 3.248632928324939,
      "average utilization" : 0.2580906137808111,
      "makespan" : 16648.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.0,
      "coefficient of variation" : 0.770785662081438,
      "standard deviation" : 0.19893254462004717,
      "Jain index" : 0.6273090710279579,
      "balancing efficiency" : 0.26638708111401704
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average slowdown" : 3.4402475628622264,
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
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average slowdown" : 1.3784888527530137,
      "min-max slowdown" : 3.5145885504629892,
      "average utilization" : 0.38652059082461115,
      "makespan" : 10223.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.14100838146523648,
      "coefficient of variation" : 0.45128883338982134,
      "standard deviation" : 0.17443242651438326,
      "Jain index" : 0.830798283124558,
      "balancing efficiency" : 0.5147956746428106
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average slowdown" : 5.441932184990949,
      "min-max slowdown" : 21.8198439826118,
      "average utilization" : 0.6054212720388572,
      "makespan" : 26147.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.38350646867631133,
      "coefficient of variation" : 0.25195537285309677,
      "standard deviation" : 0.15253914232974639,
      "Jain index" : 0.9403078386243475,
      "balancing efficiency" : 0.6053132368873877
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average utilization" : 0.32120099994681134,
      "makespan" : 12534.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.0,
      "coefficient of variation" : 0.5933016987362275,
      "standard deviation" : 0.19056909890421808,
      "Jain index" : 0.7396411924871215,
      "balancing efficiency" : 0.36295828825579995
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average slowdown" : 3.498934340916211,
      "min-max slowdown" : 13.212396748312678,
      "average utilization" : 0.556656808165159,
      "makespan" : 28740.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.29866254404256853,
      "coefficient of variation" : 0.3509209680965056,
      "standard deviation" : 0.19534254601882836,
      "Jain index" : 0.890356571774958,
      "balancing efficiency" : 0.5751893530835791
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average slowdown" : 1.382925,
      "min-max slowdown" : 3.804,
      "average utilization" : 0.4260304611779742,
      "makespan" : 9389.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.14285714285714288,
      "coefficient of variation" : 0.4330127018922193,
      "standard deviation" : 0.18447660108306285,
      "Jain index" : 0.8421052631578949,
      "balancing efficiency" : 0.5714285714285715
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average utilization" : 0.6153136176595007,
      "makespan" : 26003.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.3846153846153846,
      "coefficient of variation" : 0.24286699034656817,
      "standard deviation" : 0.14943936644022188,
      "Jain index" : 0.9443009959424564,
      "balancing efficiency" : 0.6153846153846152
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average slowdown" : 1.22385,
      "min-max slowdown" : 2.3005,
      "average utilization" : 0.31948881789137384,
      "makespan" : 12520.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.0,
      "coefficient of variation" : 0.6123724356957944,
      "standard deviation" : 0.1956461455897107,
      "Jain index" : 0.7272727272727277,
      "balancing efficiency" : 0.33333333333333337
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "RandomScheduler",
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
      "average slowdown" : 3.4042656250000007,
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
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "average slowdown" : 0.9993620727901197,
      "min-max slowdown" : 1.001695611260586,
      "average utilization" : 0.3995520254406633,
      "makespan" : 9321.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.0,
      "coefficient of variation" : 0.77808586934006,
      "standard deviation" : 0.31088578506158027,
      "Jain index" : 0.6228908836558473,
      "balancing efficiency" : 0.4928082903306238
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "average slowdown" : 4.689580901629658,
      "min-max slowdown" : 13.36308871234024,
      "average utilization" : 0.9400804402557625,
      "makespan" : 16722.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.9193956564354187,
      "coefficient of variation" : 0.021061815419059552,
      "standard deviation" : 0.01979980071173511,
      "Jain index" : 0.9995565966250213,
      "balancing efficiency" : 0.9588713431800402
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "average slowdown" : 0.9997095747790915,
      "min-max slowdown" : 1.0008322032924715,
      "average utilization" : 0.36543277031018795,
      "makespan" : 10641.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.0,
      "coefficient of variation" : 0.8660845845744354,
      "standard deviation" : 0.3164956890639842,
      "Jain index" : 0.5713951015074749,
      "balancing efficiency" : 0.4373263753916677
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "average slowdown" : 2.72648104089956,
      "min-max slowdown" : 7.008423451086213,
      "average utilization" : 0.8796363501790493,
      "makespan" : 18128.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.8257580940605063,
      "coefficient of variation" : 0.050712680835668156,
      "standard deviation" : 0.044608717478082156,
      "Jain index" : 0.9974348210680664,
      "balancing efficiency" : 0.8928235419231515
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "average slowdown" : 0.9994901144746567,
      "min-max slowdown" : 1.0011179771876737,
      "average utilization" : 0.43001878568919344,
      "makespan" : 9046.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.0,
      "coefficient of variation" : 0.7921245074067146,
      "standard deviation" : 0.3406284187896859,
      "Jain index" : 0.614453959547623,
      "balancing efficiency" : 0.4570953134578776
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "average slowdown" : 4.648969860804801,
      "min-max slowdown" : 10.936108458009105,
      "average utilization" : 0.9610474770306314,
      "makespan" : 16537.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.9253969215339504,
      "coefficient of variation" : 0.022800803857406352,
      "standard deviation" : 0.021912655021430662,
      "Jain index" : 0.9994803934747589,
      "balancing efficiency" : 0.9652131167385782
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "average slowdown" : 0.9997332948297994,
      "min-max slowdown" : 1.0004623662006484,
      "average utilization" : 0.3663997543321691,
      "makespan" : 10502.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.0,
      "coefficient of variation" : 0.8583300297957787,
      "standard deviation" : 0.3144919120530967,
      "Jain index" : 0.5757945947971398,
      "balancing efficiency" : 0.4301631900553432
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "average slowdown" : 2.7236268627743936,
      "min-max slowdown" : 5.276531436303107,
      "average utilization" : 0.8803128710565767,
      "makespan" : 18158.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.8516917989601552,
      "coefficient of variation" : 0.04812246124471501,
      "standard deviation" : 0.04236282202064392,
      "Jain index" : 0.9976895791300373,
      "balancing efficiency" : 0.8934723647310409
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "average utilization" : 0.43686014372581533,
      "makespan" : 9045.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.0,
      "coefficient of variation" : 0.7155223100940483,
      "standard deviation" : 0.31258317922671336,
      "Jain index" : 0.661387832205538,
      "balancing efficiency" : 0.542178924259056
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "average slowdown" : 4.572518405846836,
      "min-max slowdown" : 11.500393811533053,
      "average utilization" : 0.9582294188861985,
      "makespan" : 16520.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.9295700128202519,
      "coefficient of variation" : 0.019216541389333614,
      "standard deviation" : 0.01841385528850373,
      "Jain index" : 0.9996308608510619,
      "balancing efficiency" : 0.9663997476648826
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "min-max utilization" : 0.0,
      "coefficient of variation" : 0.8751378295689177,
      "standard deviation" : 0.3405089938457999,
      "Jain index" : 0.5662943139483534,
      "balancing efficiency" : 0.4566448636999509
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "average slowdown" : 2.660175752722531,
      "min-max slowdown" : 5.872065384180517,
      "average utilization" : 0.9165988694091135,
      "makespan" : 17454.0
    },
    "uniformityMetrics" : {
      "min-max utilization" : 0.8576506955177743,
      "coefficient of variation" : 0.04325636802602556,
      "standard deviation" : 0.03964873802739956,
      "Jain index" : 0.9981323811518029,
      "balancing efficiency" : 0.9272594667697063
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "min-max utilization" : 0.0,
      "coefficient of variation" : 0.7202430145443967,
      "standard deviation" : 0.3230151427489165,
      "Jain index" : 0.6584362139917694,
      "balancing efficiency" : 0.5714285714285714
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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
      "min-max utilization" : 0.0,
      "coefficient of variation" : 0.8215838362577491,
      "standard deviation" : 0.3313172038543196,
      "Jain index" : 0.5970149253731343,
      "balancing efficiency" : 0.5
    }
  }
}, {
  "config" : {
    "scheduler" : {
      "type" : "MasterSlaveScheduler"
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