import React, { useRef, useEffect } from 'react';
import {
  Chart,
  ScatterController,
  LinearScale,
  PointElement,
  Tooltip,
  Legend,
  LineElement
} from 'chart.js';

Chart.register(ScatterController, LinearScale, PointElement, Tooltip, Legend, LineElement);

const SFHChart = ({ poa, fundalHeight, data }) => {
  const canvasRef = useRef(null);

  useEffect(() => {
    const ctx = canvasRef.current.getContext('2d');


    const existingChart = Chart.getChart(ctx);
    if (existingChart) {
      existingChart.destroy();
    }

    const chart = new Chart(ctx, {
      type: 'scatter',
      data: {
        datasets: [
          {
            label: 'SFH Measurement',
            data: data,
            backgroundColor: 'red',
            pointRadius: 6,
            showLine: false,
          },
          {
            label: 'Expected SFH Line',
            data: Array.from({ length: 43 }, (_, i) => ({ x: i, y: i })), // Line y = x
            borderColor: 'green',
            borderWidth: 1,
            showLine: true,
            fill: false,
            pointRadius: 0
          }
        ]
      },
      options: {
        scales: {
          x: {
            type: 'linear',
            position: 'bottom',
            title: { display: true, text: 'POA (weeks)' },
            min: 0,
            max: 42,
            ticks: { stepSize: 2 }
          },
          y: {
            type: 'linear',
            title: { display: true, text: 'Fundal Height (cm)' },
            min: 0,
            max: 50,
            ticks: { stepSize: 5 }
          }
        },
        plugins: {
          legend: { display: true },
          tooltip: {
            callbacks: {
              label: function (context) {
                const { x, y } = context.parsed;
                return `POA: ${x} weeks, Fundal Height: ${y} cm`;
              }
            }
          }
        }
      }
    });

    return () => chart.destroy();
  }, [poa, fundalHeight]);

  return <canvas ref={canvasRef} />;
};

export default SFHChart;
