import React, { useRef, useEffect } from 'react';
import {
  Chart,
  ScatterController,
  LinearScale,
  PointElement,
  Tooltip,
  Legend
} from 'chart.js';

Chart.register(ScatterController, LinearScale, PointElement, Tooltip, Legend);

const rangePlugin = {
  id: 'rangePlugin',
  beforeDraw(chart) {
    const {
      ctx,
      chartArea: { left, right, top, bottom },
      scales: { y }
    } = chart;

    // for  a fixed height
    const height = chart.options.customHeight;
    const hM = height / 100;

    const bmiToWeight = (bmi) => bmi * hM * hM;

    const bands = [
      { to: 45, color: 'rgba(255,200,200,0.3)' }, // low 
      { from: 45, to: 65, color: 'rgba(200,255,200,0.3)' }, // normal
      { from: 65, to: 80, color: 'rgba(200,200,255,0.3)' }, // overweight
      { from: 80, color: 'rgba(255,255,200,0.3)' } // obese
    ];

    bands.forEach(b => {
      const y1 = b.from != null ? y.getPixelForValue(b.from) : bottom;
      const y2 = b.to != null ? y.getPixelForValue(b.to) : top;
      ctx.fillStyle = b.color;
      ctx.fillRect(left, y2, right - left, y1 - y2);
    });
  }
};

const BMIChart = ({ poa, weight, height, bmi, bmiHistory }) => {
  const canvasRef = useRef(null);

  useEffect(() => {
    const ctx = canvasRef.current.getContext('2d');

    const chart = new Chart(ctx, {
      type: 'scatter',
      data: {
        datasets: [
          {
            label: 'BMI History',
            data: bmiHistory,
            backgroundColor: 'blue',
            pointRadius: 5,
          },
          {
            label: 'Your BMI',
            data: poa && weight ? [{ x: Number(poa), y: Number(weight) }] : [],
            backgroundColor: 'red',
            pointRadius: 6,
            showLine: false,
            bmi: bmi
          }
        ]
      },
      options: {
        customHeight: height, // pass height for bmi range calculation
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
            title: { display: true, text: 'Weight (kg)' },
            min: 30,
            max: 120,
            ticks: { stepSize: 5 }
          }
        },
        plugins: {
          legend: { display: false },
          tooltip: {
            callbacks: {
              label: function (context) {
                const chartHeight = context.chart.options.customHeight;
                const weight = context.parsed.y;
                const h = chartHeight / 100;
                const bmi = (weight / (h * h)).toFixed(2);
                return `BMI: ${bmi}`;
              }
            }
          }
        }
      },
      plugins: [rangePlugin]
    });

    return () => chart.destroy();
  }, [poa, weight, height, bmiHistory]);

  return <canvas ref={canvasRef} />;
};

export default BMIChart;
